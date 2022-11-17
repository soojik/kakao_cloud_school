const e = require("express");
const express = require("express");

// 웹 서버 생성할 수 있는 인스턴스 app 생성
const app = express();

const port_num = 8000;
app.set("port", port_num);

// 요청 처리하는 함수 외부에 만든 변수는
// 모든 사용자가 공유
let num = 1;

const session = require("express-session");
// 세션을 사용하기 위한 미들웨어 설정
// 세션은 클라이언트에 키를 만들어서 매핑
// 이때 key 의 값을 알아보기 어렵게 하기 위해서 연산을 수행할 값을 주게 되는데 이 값이 secret
app.use(session({
    secret:"only cat saves us",
    resave:false,
    saveUninitialized:true
}))

// 사용자 요청 처리하는 부분
app.get("/session", (req, res) => {
    // 세션에 num 값 없으면 1로 초기화, 있으면 1 증가
    if (!req.session.num) {
        req.session.num = 1;
    } else {
        req.session.num++;
    }

    // 내용을 화면에 출력
    res.send("num : " + num + "\nsession.num : " + req.session.num);
    num++;
});

app.listen(app.get("port"), () => {
    console.log("서버 대기 중 ...");
});
