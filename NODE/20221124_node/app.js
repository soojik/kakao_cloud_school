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
    {path:path.join(__dirname, '.env')}
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
    date_format:'YYYYMMDD',
    filename:path.join(logDirectory, 'access-%DATE%.log'),
    frequency:'daily',
    verbose:false
});

// 로그 기록 설정
app.use(morgan('combined', {stream:accessLogStream}));

// 압축해서 전송하는 옵션 설정
// 실제 눈으로 보이는 결과는 없지만, 에러 안나면 성공적으로 설정 완
app.use(compression());

// POST 방식의 파라미터 읽을 수 있도록 설정
let body_parser = require('body-parser');
app.use(body_parser.json());
app.use(body_parser.urlencoded({
    extended:true
}));

// session을 데이터베이스에 저장하는 작업

// DB 접속 정보
let options  = {
    host:process.env.HOST,
    port:process.env.MYSQLPORT,
    user:process.env.MYSQLUSER,
    password:process.env.PASSWORD,
    database:process.env.DATABASE
};

// session 저장하기 위한 mysql DB 저장소 생성
const MariaDBStore = require('express-mysql-session')(session);

// session 설정
app.use(session({
    secret:process.env.COOKIE_SECRET,
    resave:false,
    saveUninitialized:true,
    store:new MariaDBStore(options)
}))

// 파일 업로드 설정
const upload = multer({
    storage:multer.diskStorage({
        destination(req, file, done){
            done(null, 'public/img');
        },
        filename(req, file, dome){
            const ext = path.extname(file.originalname);
            // 원래 파일 이름+날짜.확장자
            done(null, path.basename(file.originalname, ext) + Date.now() + ext);
        }
    }),
    limits:{
        fileSize:10*1024*1024
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
})

// 기본 요청 처리
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html'));
});

// 데이터 전체 가져오기 처리
app.get('/artists/all', (req, res) => {
    // HTML 출력 : res.sendFile(파일 경로)
    // 서버의 데이터 출력 못함 - ajax 나 fetch api 사용

    // 템플릿 엔진: res.render(파일 경로, 데이터)
    // 템플릿 엔진에서 넘겨주는 데이터는 프로그래밍 언어의 데이터

    // JSON 출력: res.json(데이터)
    // json 문자열의 형태로 데이터 제공
    // front end에서 데이터 수신해 출력
    // connection.query('sql', [], (err, results, fields) => {})

    // 2개 이상의 데이터 조회할 때는 정렬 필수
    connection.query('SELECT * FROM ARTISTS ORDER BY ID', (err, results, fields) => {
        if (err) {
            // 에러 발생한 경우
            // 주의할 점***
            // 에러가 발생했다고 데이터 전송하지 않으면 안된다.
            res.json({'result':false});
        } else {
            // 정상 응답한 경우
            res.json({'result':true, 'list':results});
        }
    })
})

// 데이터 일부분 가져오기
// URL - /artists/list
// parameter - page (값이 undefined라면 첫페이지로 간주해 1로 설정)
app.get('/artists/list/', (req, res) => {
    let page = req.query.page;

    if (!page) {
        page = 1;
    }
    
    console.log(page);

    // artists 테이블에서 id를 가지고 내림차순 정렬로 페이지 단위로 데이터 가져우는 구문
    // SELECT * FROM ARTISTS ORDER BY ID DESC LIMIT 시작번호, 개수

    //파라미터는 무조건 문자열이기에, 파라미터를 갖고 산술연산을 할 때는 숫자로 변환을 수행해야한다.
    connection.query(
        "SELECT * FROM ARTISTS ORDER BY ID LIMIT ?, 3",
        [(page-1)*3], (err, results, fields) => {
            if (err) {
                res.json({'result':false});
                console.log(err);
            } else {
                res.json({'result':true, 'list':results});
            }
        }
    );
})

app.use((err, req, res, next) => {
    console.log(err);
    res.status(500).send(err.message);
});

app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트에서 대기 중 ...');
});