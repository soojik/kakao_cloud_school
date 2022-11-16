let today = new Date();

// 오늘 날짜 없는 디렉토리 없으면 생성

let date_info = "./" + today.getFullYear() + (today.getMonth()+1) + today.getDate();

const fs = require("fs");

const menu = ["mkdir", "rmdir", "quit"];

const print_menu = () => {
    console.log("");
    console.log("===============");
    console.log("how to use");
    console.log("mkdir");
    console.log("rmdir");
    console.log("if you want to exit, type quit");
    console.log("===============");
    console.log("");
}

print_menu();

const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

rl.on("line", line => {
    if (menu.indexOf(line) < 0) {
        console.log("there's no menu like that");
    } else {
        if (line === menu[0]) {
            fs.access(date_info, (error) => {
                if (error) {
                    console.log("create " + date_info + " directory ...");
                    fs.mkdir(date_info, (error) => {
                        if (error) {
                            console.log("somethings' wrong");
                        } else {
                            console.log("complete");
                        }
                    })
                } else {
                    console.log("already exist");
                }
                
            })
        } else if (line === menu[1]) {
            console.log("delete " + date_info + " file ...");
            fs.rmdir(date_info, () => {});
            console.log("complete");
        } else if (line === menu[2]) {
            rl.close();
        } else {
            print_menu();
        }
    }
})

rl.on("close", () => {
    console.log("bye :)");
    process.exit();
})