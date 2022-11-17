// 웹 서버 구축을 위한 외부 모듈
const { response } = require("express");
const express = require("express");
// 현 디렉토리에 대한 절대 경로를 알아내기 위한 내장 모듈
const path = require("path");

// 서버를 준비
const app = express();

// 포트번호 지정
const port_num = 8000;
app.set("port", port_num);

// 사용자의 요청 처리
// /는 포트번호까지의 요청
app.get("/index", (req, res) => {
    console.log(req.ip);
    console.log(req.query);
    // 현 디렉토리의 home.html 출력
    res.sendFile(path.join(__dirname, '/home.html'));
})

app.listen(app.get("port"), () => {
    console.log(app.get("port"), "번 포트에서 대기 중 ... ");
})

// 일단위 로그 기록을 위한 설정
const morgan = require("morgan");
const FileStreamRotator = require("file-stream-rotator");

// 내장 모듈
const fs = require("fs");

// 로그 파일 저장할 디렉토리 하나 생성
const logDirectory = path.join(__dirname, 'log');

// 디렉토리 존재 여부 확인 후, 없으면 생성
fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory);

// 일단위 로그 기록 설정
const accessLogStream = FileStreamRotator.getStream({
    date_format:"YYYYMMDD",
    filename:path.join(logDirectory, "access-%DATE%.log"),
    frequency:"daily",
    verbose:true
});

app.use(morgan("combined", {stream:accessLogStream}));
