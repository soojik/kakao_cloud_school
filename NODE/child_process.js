// 다른 프로세스를 실행할 수 있는 모듈 가져오기
const exec = require("child_process").exec;

// 프로세스 준비
let process = exec('ls');

console.log("whats in this directory?");

// 프로세스 정상적으로 수행되면
process.stdout.on("data", (data) => {
    console.log(data.toString());

    console.log("open notepad");
    process = exec("notepad ./app.js");
});

process.stderr.on("data", (e) => {
    console.log("something's wrong");
});

