// export.js 에서 내보낸 내용 가져오기
const {peter, cider} = require("./export");

const print_quote = name => {
    if (name == peter) {
        return "사슴 일은 아무도 모른다.";   
    }
    if (name == cider) {
        return "고양이도 밟으면 꿈틀한다";
    }
    else {
        return "no data";
    }
}

module.exports = print_quote;