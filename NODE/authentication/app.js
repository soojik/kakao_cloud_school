const express = require('express');

const dotenv = require('dotenv');
dotenv.config();

//서버 설정
const app = express();
app.set('port', process.env.PORT);

//로그 출력 설정
const fs = require('fs');
const path = require('path');

//static 파일의 경로 설정
app.use(express.static(path.join(__dirname, 'public')));

//view template 설정
const nunjucks = require('nunjucks');
app.set('view engine', 'html');
nunjucks.configure('views', {
    express: app,
    watch: true,
});

const morgan = require('morgan');
const FileStreamRotator = require('file-stream-rotator');

const logDirectory = path.join(__dirname, 'log');

// 로그 디렉토리 생성
fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory);

// 로그 파일 옵션 설정
const accessLogStream = FileStreamRotator.getStream({
    date_format: 'YYYYMMDD',
    filename: path.join(logDirectory, 'access-%DATE%.log'),
    frequency: 'daily',
    verbose: false
});

// 로그 설정
app.use(morgan('combined', { stream: accessLogStream }));

//출력하는 파일 압축해서 전송
const compression = require('compression');
app.use(compression());

//post 방식의 파라미터 읽기
let bodyParser = require('body-parser');
app.use(bodyParser.json()) // for parsing application/json
app.use(bodyParser.urlencoded({ extended: true }))

//쿠키 설정
const cookieParser = require('cookie-parser');
app.use(cookieParser(process.env.COOKIE_SECRET));

//세션 설정
const session = require("express-session");
let options = {
    host: process.env.HOST,
    port: process.env.MYSQLPORT,
    user: process.env.USERID,
    password: process.env.PASSWORD,
    database: process.env.DATABASE
};

const MySQLStore = require('express-mysql-session')(session);

app.use(
    session({
        secret: process.env.COOKIE_SECRET,
        resave: false,
        saveUninitialized: true,
        store: new MySQLStore(options)
    })
);

const { sequelize } = require('./models');
sequelize.sync({ force: false })
    .then(() => {
        console.log("DB 연결 성공");
    })
    .catch((err) => {
        console.log(err);
    })

const passport = require('passport');
const passport_config = require('./passport');
passport_config();

app.use(passport.initialize());
// session 기능은 passport 모듈이 알아서 사용
app.use(passport.session());

// 일반적인 설정이 끝난 후, 에러 설정 직전에 코드 삽입해주면 된다.
//라우터 설정
const pageRouter = require('./routes/page');
app.use('/', pageRouter);

// 로그인 관련 라우터 설정
const authRouter = require('./routes/auth');
app.use('/auth', authRouter);

// post 관련 라우터 설정
const postRouter = require('./routes/post');
app.use('/post', postRouter);

// follow, user 라우터 연결
const userRouter = require('./routes/users');
app.use('/user', userRouter);

//에러가 발생한 경우 처리 - 404
app.use((req, res, next) => {
    const err = new Error(`${req.method} ${req.url} 라우터가 없습니다.`);
    err.status = 404;
    next(err);
});

//에러가 발생한 경우 처리 - 404 이외의 에러
app.use((err, req, res, next) => {
    res.locals.message = err.message;
    res.locals.error = process.env.NODE_ENV !== 'production' ? err : {};
    res.status(err.status || 500);
    res.render('error');
});

app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트에서 대기 중');
});
