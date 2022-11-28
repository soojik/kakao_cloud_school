const express = require('express');
const morgan = require('morgan');
const compression = require('compression');
const path = require('path');
const mysql = require('mysql');
const cookie_parser = require('cookie-parser');
const session = require('express-session');
const multer = require('multer');
const dotenv = require('dotenv');

dotenv.config(
    { path: path.join(__dirname, '.env') }
);

const app = express();
app.set('port', process.env.PORT || 9000);

let FileStreamRotator = require('file-stream-rotator');
let fs = require('fs');
const { Frequency } = require('file-stream-rotator/lib/enums');

let logDirectory = path.join(__dirname, 'log');

fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory);

// 로그 파일 옵션 설정
let accessLogStream = FileStreamRotator.getStream({
    date_format: 'YYYYMMDD',
    filename: path.join(logDirectory, 'access-%DATE%.log'),
    frequency: 'daily',
    verbose: false
});

// 로그 기록 설정
app.use(morgan('combined', { stream: accessLogStream }));

// 압축해서 전송하는 옵션 설정
// 실제 눈으로 보이는 결과는 없지만, 에러 안나면 성공적으로 설정 완
app.use(compression());

// POST 방식의 파라미터 읽을 수 있도록 설정
let body_parser = require('body-parser');
app.use(body_parser.json());
app.use(body_parser.urlencoded({
    extended: true
}));

// session을 데이터베이스에 저장하는 작업

// DB 접속 정보
let options = {
    host: process.env.HOST,
    port: process.env.MYSQLPORT,
    user: process.env.MYSQLUSER,
    password: process.env.PASSWORD,
    database: process.env.DATABASE
};

// session 저장하기 위한 mysql DB 저장소 생성
const MariaDBStore = require('express-mysql-session')(session);

// session 설정
app.use(session({
    secret: process.env.COOKIE_SECRET,
    resave: false,
    saveUninitialized: true,
    store: new MariaDBStore(options)
}))

// 파일 업로드 설정
const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, done) {
            done(null, 'public/img');
        },
        filename(req, file, done) {
            const ext = path.extname(file.originalname);
            // 원래 파일 이름+날짜.확장자
            done(null, path.basename(file.originalname, ext) + Date.now() + ext);
        }
    }),
    limits: {
        fileSize: 10 * 1024 * 1024
    }
});

// 정적 파일 경로 설정
app.use('/', express.static('public'));

// 파일 다운로드 위한 모듈
let util = require('util');
let mime = require('mime');

// 데이터베이스 연결
let connection = mysql.createConnection(options);
connection.connect((error) => {
    if (error) {
        console.log(error);
        throw error;
    }
});

// sequelize 를 이용한 데이터베이스 연결
// require 할 때 디렉토리 이름 기재하면 디렉토리 안에 index.js 내용 import
const { sequelize } = require('./models');
const { artists } = require('./models');
const Artist = require('./models/artist');

sequelize.sync({ force: false })
    .then(() => {
        // 연결에 성공했을 때
        console.log("DB 연결 성공");
    })
    .catch((err) => {
        // 연결 실패
        console.log("DB 연결 실패");
    });

// 기본 요청 처리
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html'));
});

// 데이터 전체 가져오기 처리
app.get('/artists/all', async (req, res) => {
    // HTML 출력 : res.sendFile(파일 경로)
    // 서버의 데이터 출력 못함 - ajax 나 fetch api 사용

    // 템플릿 엔진: res.render(파일 경로, 데이터)
    // 템플릿 엔진에서 넘겨주는 데이터는 프로그래밍 언어의 데이터

    // JSON 출력: res.json(데이터)
    // json 문자열의 형태로 데이터 제공
    // front end에서 데이터 수신해 출력
    // connection.query('sql', [], (err, results, fields) => {})

    // 2개 이상의 데이터 조회할 때는 정렬 필수
    // connection.query('SELECT * FROM artists ORDER BY id', (err, results, fields) => {
    //     if (err) {
    //         // 에러 발생한 경우
    //         // 주의할 점***
    //         // 에러가 발생했다고 데이터 전송하지 않으면 안된다.
    //         res.json({ 'result': false });
    //     } else {
    //         // 정상 응답한 경우
    //         res.json({ 'result': true, 'list': results });
    //     }
    // })

    try {
        let list = await Artist.findAll();
        res.json({ 'result': true, 'list': list });
    } catch (err) {
        console.log(err);
        res.json({ 'result': false });
    }
})

// 데이터 일부분 가져오기
// URL - /artists/list
// parameter - page (값이 undefined라면 첫페이지로 간주해 1로 설정)
app.get('/artists/list/', async (req, res) => {
    let page = req.query.page;

    if (!page) {
        page = 1;
    }

    // artists 테이블에서 id를 가지고 내림차순 정렬로 페이지 단위로 데이터 가져우는 구문
    // SELECT * FROM ARTISTS ORDER BY ID DESC LIMIT 시작번호, 개수

    //파라미터는 무조건 문자열이기에, 파라미터를 갖고 산술연산을 할 때는 숫자로 변환을 수행해야한다.
    // 성공과 실패 여부 저장
    let result = true;
    // 성공했을 때 데이터 저장하기 위한
    let list;
    // 데이터 목록 가져오기
    // connection.query(
    //     "SELECT * FROM artists ORDER BY id LIMIT ?, 3",
    //     [(page - 1) * 3], (err, results, fields) => {
    //         if (err) {
    //             console.log(err);
    //             result = false;
    //         } else {
    //             list = results;

    //             // 비동기로 실행되기 때문에 count가 먼저 나와버리면 0 리턴하게 되므로
    //             let cnt;
    //             connection.query("SELECT COUNT(*) CNT FROM artists", (err, results, fields) => {
    //                 if (err) {
    //                     console.log(err);
    //                     result = false;
    //                 } else {
    //                     cnt = results[0].CNT;
    //                 }

    //                 if (result === false) {
    //                     res.json({ 'result': false });
    //                 } else {
    //                     res.json({ 'result': true, 'list': list, 'count': cnt });
    //                 }
    //             });


    //         }
    //     }
    // );

    try {
        let cnt = await Artist.count();
        //페이지 단위로 데이터 목록 가져오기
        let list = await Artist.findAll({
            offset:(parseInt(page)-1)*3,
            limit:3
        });

        res.json({'result':true, 'list':list, 'count':cnt});
    } catch (err) {
        console.log(err);
        res.json({ 'result': false });
    }

});

// 상세보기 처리 위한 코드
app.get('/artists/detail/:ID', async (req, res) => {
    //파라미터 읽기
    let id = req.params.ID;
    //id 를 이용해서 1개의 데이터를 찾아오기
    // connection.query("SELECT * FROM artists WHERE ID=?", [id], (err, results, fields) => {
    //     if (err) {
    //         console.log(err);
    //         res.json({ 'result': false });
    //     } else {
    //         res.json({ 'result': true, 'data': results[0] });
    //     }
    // });

    try {
        let data = await Artist.findOne({
            where:{
                id:id
            }
        })
        res.json({'result':true, 'data':data});
    } catch(err) {
        console.log(err);
        res.json({'result':false});
    }
});

app.get('/artists/img/:pictureurl', (req, res) => {
    let pictureurl = req.params.pictureurl;

    let file =
        "C:\\Users\\user\\kakao_cloud_school\\NODE\\20221124_node\\public\\img" + "/" + pictureurl;

    console.log(__dirname);

    let mimetype = mime.lookup(pictureurl);
    res.setHeader('Content-disposition', 'attachment; filename=' + pictureurl);
    res.setHeader('Content-Type', mimetype);
    // 파일의 내용 읽어서 res 전송
    let fileStream = fs.createReadStream(file);
    fileStream.pipe(res);
});

// 현재 날짜 문자열로 리턴하는 함수
// 요즘 등장하는 자바스크립트 라이브러리들의 샘플 예제는 특별한 경우가 아니면 <function>을 이제 잘 사용하지 않음ㅇ
const getDate = () => {
    let today = new Date();

    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    let date = today.getDate();

    month = month >= 10 ? month : '0' + month;
    date = date >= 10 ? date : '0' + date;

    return year + '-' + month + '-' + date;
}

const getTime = () => {
    let today = new Date();

    let hour = today.getHours();
    let minute = today.getMinutes();
    let second = today.getSeconds();

    hour = hour >= 10 ? hour : '0' + hour;
    minute = minute >= 10 ? minute : '0' + minute;
    second = second >= 10 ? second : '0' + second;

    return hour + ':' + minute + ':' + second;
}

app.post('/artists/insert', upload.single('pictureurl'), async (req, res) => {
    const name = req.body.name;
    const description = req.body.description;
    const region = req.body.region;

    let pictureurl;
    if (req.file) {
        pictureurl = req.file.filename;
    } else {
        pictureurl = 'default.jpg';
    }

    let id = 1;
    try {
        let x = await Artist.max('id');
        id = x + 1;
    } catch (err) {
        console.log(err);
    }

    Artist.create({
        //속성과 값만 주면 돼
        id: id,
        name: name,
        region: region,
        description: description,
        pictureurl: pictureurl,
        updatedate: getDate()
    });

    const writeStream = fs.createWriteStream('./update.txt');
    writeStream.write(getTime());
    writeStream.end();

    res.json({ 'result': true });

    // connection.query('select max(id) max_id from artists', (err, results, fields) => {
    //     if (err) {
    //         console.log(err);
    //     } else {
    //         let id;
    //         if (results.length > 0) {
    //             id = results[0].max_id + 1;
    //         } else {
    //             id = 1;
    //         }

    //         connection.query('insert into artists(id, name, region, pictureurl, description, updatedate) values (?, ?, ?, ?, ?, ?)',
    //             [id, name, region, pictureurl, description, getDate()], (err, results, fields) => {
    //                 if (err) {
    //                     console.log(err);
    //                     res.json({'result':false});
    //                 } else {
    //                     const writeStream = fs.createWriteStream('./update.txt');
    //                     writeStream.write(getTime());
    //                     writeStream.end();

    //                     res.json({'result':true});
    //                 }
    //             });
    //     }
    // });

});

app.post('/artists/delete', async(req, res) => {
    let id = req.body.id;

    // connection.query('delete from artists where id = ?', [id], (err, results, fields) => {
    //     if (err) {
    //         console.log(err);
    //         res.json({ 'result': false });
    //     } else {
    //         const writeStream = fs.createWriteStream('./update.txt');
    //         writeStream.write(getTime());
    //         writeStream.end();

    //         res.json({ 'result': true });
    //     }
    // });

    try {
        await Artist.destroy({
            where:{id:id}
        })
        res.json({'result':true});
    } catch(err) {
        console.log(err);
        res.json({'result':false});
    }
});

// 폼 데이터 읽어 수정
app.get('/artists/update', (req, res) => {
    //public 디렉토리의 update.html 읽어내서 리턴
    fs.readFile('./public/update.html', (err, data) => {
        res.end(data);
    })
});

app.post('/artists/update', upload.single('pictureurl'), async (req, res) => {
    const id = req.body.id;
    const name = req.body.name;
    const description = req.body.description;
    const region = req.body.region;

    const oldpictureurl = req.body.oldpictureurl;

    console.log(id + " " + name + " " + description + " " + region);

    let pictureurl;
    if (req.file) {
        pictureurl = req.file.filename;
    } else {
        pictureurl = oldpictureurl;
    }

    // connection.query('update artists set name=?, region=?, description=?, pictureurl=?, updatedate=? where id=?', [name, region, description, pictureurl, getDate(), id], (e, results, fields) => {
    //     if (e) {
    //         console.log(e);
    //         res.json({ 'result': false });
    //     } else {
    //         const writeStream = fs.createWriteStream('./update.txt');
    //         writeStream.write(getTime());
    //         writeStream.end();
    //         res.json({ 'result': true });
    //     }
    // });

    try {
        await Artist.update({
            name:name,
            region:region,
            description:description,
            pictureurl:pictureurl,
            updatedate:getDate()
        }, {where : {id:id}})
        res.json({'result':true});
    } catch(err) {
        console.log(err);
        res.json({'result':false});
    }

});

app.get('/artists/updatedate', (req, res) => {
    fs.readFile('./update.txt', (err, data) => {
        res.json({ 'result': data.toString() });
    })
})


app.use((err, req, res, next) => {
    console.log(err);
    res.status(500).send(err.message);
});

app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트에서 대기 중 ...');
});