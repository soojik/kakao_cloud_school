const http = require("http");
const fs = require("fs").promises;

const port_num = 8000;

// 서버 생성 - 포트번호는 일반적으로 1024 까지는 예약되어 있어, 사용하지 않는다.
// 1521, 3306, 27017 번은 DB용 port
// 8080 : tomcat과 같은 WAS, Web Container에서 많이 사용
// 80 : http 사용하면 default이기에 포트번호 생략 가능
// 443 : https 사용하면 default이기에 포트번호 생략 가능
http.createServer(async(request, response) => {
    try {
        // 파일의 내용 읽어 data에 저장
        // 다음 명령은 이 명령이 끝나고 나면 수행
        const data = await fs.readFile("./index.html");
        response.writeHead(200, {"Content-Type" : "text/html;charset=utf-8"});

        response.end(data);
    } catch(error) {
        response.writeHead(500, {"Content-Type" : "text/html;charset=utf-8"});

        response.end(error.message);
    }

}).listen(port_num, () => {
    console.log("8000 번 포트에서 서버 대기 중 ...");
});