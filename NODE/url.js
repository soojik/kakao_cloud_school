const url = require("url");
const addr = "https://www.naver.com/login?id=soo";

const p = url.parse(addr);
console.log(p);

// addr 에서 파라미터 부분만 가져오기
// searchParams 속성을 호출하면 파라미터 부분에 해당하는 객체를 리턴

const addr2 = new URL("https://www.naver.com/login?id=soo");

console.log(addr2.searchParams);
console.log("id의 값만 추출");
console.log(addr2.searchParams.get("id"));