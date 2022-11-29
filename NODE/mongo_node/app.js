const express = require('express');
const morgan = require('morgan');
const path = require('path');
const multer = require('multer');
const fs = require('fs');
const body_parser = require('body-parser');
const util = require('util');
const mime = require('mime');

const app = express();
app.set('port', process.env.PORT || 8000);

// 로그 출력
app.use(morgan('dev'));

// form 이 아닌 형태의 POST 방식의 파라미터 읽기 위한 설정
app.use(body_parser.json());
app.use(body_parser.urlencoded({
    extended: true
}));

try {
    fs.readdirSync('img');
} catch (err) {
    console.error('img 폴더가 없어 img 폴더 생성 ~ ');
    fs.mkdirSync('img');
}

const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, done) {
            done(null, 'img/');
        },
        filename(req, file, done) {
            const ext = path.extname(file.originalname);
            done(null, path.basename(file.originalname, ext) + Date.now() + ext);
        }
    }),
    limits: { fileSize: 10 * 1024 * 1024 }
});

app.set('view engine', 'html');
app.engine('html', require('ejs').renderFile);

// mongo DB 사용을 위한 모듈 가져오기
const mongo_client = require('mongodb').MongoClient;

// 접속할 데이터베이스 URL 설정
const databaseUrl = 'mongodb://127.0.0.1:27017/';

// node DB의 artist 컬렉션에 존재하는 모든 데이터 리턴
app.get('/artist', (req, res, next) => {

    mongo_client.connect(databaseUrl, { useNewUrlParser: true }, (err, database) => {
        if (err) {
            console.log(err);
            res.json({ 'result': false, 'message': '...' });
        } else {
            let db = database.db('soo');

            // artist 컬렉션의 모든 데이터 가져오기
            db.collection('artist').find().toArray((err, data) => {
                if (err) {
                    console.log(err);
                    res.json({ 'result': false, 'message': '...' });
                } else {
                    res.json({ 'result': true, 'list': data, 'count': data.length });
                }
            });
        }
    })
});

app.get('/artist/paging', (req, res, next) => {

    let pageno = req.query.pageno;
    let count = req.query.count;

    if (pageno === undefined) {
        pageno = 1;
    }
    if (count === undefined) {
        count = 2;
    }

    // 웹에서 클라이언트가 전송하는 데이터는 기본적으로 문자열이기 때문에 parseInt(문자열) 숫자로 변경해야한다.
    let start = (parseInt(pageno) - 1) * parseInt(count);

    mongo_client.connect(databaseUrl, { useNewUrlParser: true }, (err, database) => {
        if (err) {
            console.log(err);
            res.json({ 'result': false, 'message': '...' });
        } else {
            let db = database.db('soo');

            // artist 컬렉션의 모든 데이터 가져오기
            db.collection('artist').find().skip(start).limit(parseInt(count)).toArray((err, data) => {
                if (err) {
                    console.log(err);
                    res.json({ 'result': false, 'message': '...' });
                } else {
                    res.json({ 'result': true, 'list': data, 'count': data.length });
                }
            });
        }
    })
});

// 상세보기
// 기본키 하나의 데이터를 필요로 하는 경우가 많고
// 하나, 그리고 그 이외의 주위의 3-10 여개의 데이터를 같이 리턴하는 경우
app.get('/artist/:id', (req, res, next) => {

    let id = req.params.id;

    mongo_client.connect(databaseUrl, { useNewUrlParser: true }, (err, database) => {
        if (err) {
            console.log(err);
            res.json({ 'result': false });
        } else {
            let db = database.db('soo');

            db.collection('artist').findOne({ '_id': Number(id) }, (err, data) => {
                if (err) {
                    console.log(err);
                    res.json({ 'result': false });
                } else {
                    res.json({ 'result': true, 'data': data });
                }
            });
        }
    });
});

// 데이터 삽입 구현
// name, description, region, pictureurl(파일) 받기
app.post('/artist/insert', upload.single('pictureurl'), (req, res) => {
    const name = req.body.name;
    const description = req.body.description;
    const region = req.body.region;

    let pictureurl;
    if (req.file) {
        pictureurl = req.file.filename;
    } else {
        pictureurl = 'default.jpg';
    }

    mongo_client.connect(databaseUrl, { useNewUrlParser: true }, (err, database) => {
        if (err) {
            console.log(err);
            res.json({ 'result': false });
        } else {
            let db = database.db('soo');

            db.collection('artist').find(
                {},
                { projection: { _id: 0, id: 1 } })
                .sort({ id: -1 }).limit(1)
                .toArray((err, result) => {
                    let id = 1;
                    if (result[0] != null)
                        id = result[0].id + 1;

                    db.collection('artist')
                        .insertOne({
                            'id': id,
                            'name': name,
                            'description': description,
                            'region': region,
                            'pictureurl': pictureurl
                        }, (err, result) => {
                            if (err) {
                                console.log(err);
                                res.json({ 'result': false });
                            } else {
                                console.log(err);
                                res.json({ 'result': true });
                            }
                        });
                })


        }
    })
})


// 에러 처리 위한 코드
app.use((err, res, req, next) => {
    console.log(err);
    res.status(500).send(err.message);
})

// 서버 구동
app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트 대기 중 ... ');
})