const express = require("express");
const path = require("path");
const session = require("express-session");
const dotenv = require("dotenv");
const FileStore = require("session-file-store")(session);
const morgan = require("morgan");
const fs = require("fs");
const multer = require("multer");
const app = express();

const indexRouter = require(path.join(__dirname, "./routes/index.js"));
const userRouter = require(path.join(__dirname, "./routes/user.js"));
const boardRouter = require(path.join(__dirname, "./routes/board.js"));

dotenv.config();

app.set("port", process.env.PORT);
// res.render 로 출력할 때 사용할 디렉토리 설정
app.set("views", path.join(__dirname, "views"));
// 템플릿 엔진은 pug 사용하겠다고 설정
app.set("view engine", "pug");

app.use("/", (req, res) => {
    res.render("index", {"title" : "Pug", "list" : ["peter", "cider", "natalie"]});
})

app.use(session({
    secret: process.env.COOKIE_SECRET,
    resave: false,
    saveUninitialized: true,
    store: new FileStore()
}));

// == fs.existsSync("files") || fs.mkdirSync("files")
try {
    fs.readdirSync("files");
} catch (error) {
    fs.mkdirSync("files");
}


const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, done) {
            done(null, 'files/');
        },
        filename(req, file, done) {
            // 원 파일 이름이 한글일 경우를 생각해 인코딩
            file.originalname =
                Buffer.from(file.originalname, "latin1").toString("utf-8");
            const ext = path.extname(file.originalname);
            done(null, path.basename(
                file.originalname, ext) + Date.now() + ext);
        }
    }),
    limits: {
        fileSize: 1024 * 1024 * 10
    }
})

// 로그 출력 설정
app.use(morgan("dev"));

// post 방식의 파라미터 읽을 수 있도록 설정
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

// 쿠키 사용 가능하도록 설정
const cookieParser = require("cookie-parser");
app.use(cookieParser(process.env.COOKIE_SECRET));

app.use("/", indexRouter);
app.use("/user", userRouter);
app.use("/board", boardRouter);

// 요청 처리
// 요청처리 메서드 - get, post, put(patch), delete, options
app.get("/home", (req, res) => {
    // 처리

    // 응답
    // send(직접 출력 내용 작성)
    // sendFile(html 파일 경로)
    // json(json 데이터) - 서버 랜더링을 하지 않음

    res.sendFile(path.join(__dirname, "/home.html"));
})

app.get("/upload", (req, res) => {
    res.sendFile(path.join(__dirname + "/single.html"));
});

// 파일 업로드
// post 방식의 파라미터는 req.body.파라미터 이름
app.post("/upload", upload.single("image"), (req, res) => {
    console.log(req.body.title);
    console.log(req.file.originalname);
    res.send("이미지 로드 완료");
});

app.get("/upload/multi", (req, res) => {
    res.sendFile(path.join(__dirname, "/multi.html"));
})

app.post("/upload/multi", upload.array("image"), (req, res) => {
    res.send("이미지 로드 완료");
})

app.listen(app.get("port"), () => {
    console.log(app.get("port"));
});

// 세션을 메모리에 저장하게 되면 재사용이 안되고 다른 서버와의 공유가 어렵기 때문에
// 세션을 파일에 저장해서 공유하고 재사용 가능하도록 만들 수 있다.