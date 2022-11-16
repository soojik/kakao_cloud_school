const {first, second} = require("./export");

// 데이터를 하나만 내보내고 가져올 때는 이름을 바꿔서 받을 수 있다.
const print_quote = require('./print_quote');

console.log("put name");
console.log("peter? or cider?");

const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

rl.on("line", (line) => {
    console.log(print_quote(line));
    const os = require("os");
    console.log(os.freemem());
    rl.close();
})

rl.on("close", () => {
    console.log("bye :)");
    process.exit();
})