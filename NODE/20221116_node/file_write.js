const fs = require("fs");

const file = fs.createWriteStream("./data.txt");

const list = [];
let data = fs.readFileSync("./test.txt");

let result = data.toString().split("\n");
result.forEach((e) => {
    e = e.split(":");
    list.push(e[0]);
})

for (let i=0;i<1000000;i++) {
    file.write(list[i % 6] + "\n");
}

const menu = ["copy", "copy_stream", "quit"];

const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

rl.on("line", line => {
    let cmd = line.split(" ");

    let command = cmd[0];
    if (menu.indexOf(command) < 0) {
        console.log("there's no menu like that");
    } else {
        if (command === menu[0]) {

            console.log("memory before copy data.txt : " + process.memoryUsage().rss);
            
            const data1 = fs.readFileSync('./data.txt');
            fs.writeFileSync('./copy_of_data.txt', data1);

            console.log("memory after copy data.txt : " + process.memoryUsage().rss);
        } else if (command === menu[1]) {
            console.log("memory before copy data.txt : " + process.memoryUsage().rss);

            const readStream = fs.createReadStream("./data.txt");
            const writeStream = fs.createWriteStream("./stream_data.txt");

            readStream.pipe(writeStream);
            readStream.on('end', () => {
                console.log("memory after copy data.txt with stream : " + process.memoryUsage().rss);
            })
        } else if (command === menu[2]) {
            rl.close();
        }
    }
})

rl.on("close", () => {
    fs.unlink("./data.txt", () => {});
    fs.unlink("./copy_of_data.txt", () => {});
    fs.unlink("./stream_data.txt", () => {});

    console.log("bye :)");
    process.exit();
})