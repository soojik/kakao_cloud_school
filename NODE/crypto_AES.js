const algorithm = "aes-256-cbc";

const key = "12345678901234567890123456789012";
const iv = "1234567890123456";

const crypto = require("crypto");

const encrypt = tel_num => {
    let cipher = crypto.createCipheriv(algorithm, key, iv);
    let result = cipher.update(tel_num, "utf8", "base64");

    result += cipher.final("base64");

    return result;
}

const decrypt = cipher => {
    let decipher = crypto.createDecipheriv(algorithm, key, iv);
    let result2 = decipher.update(cipher, "base64", "utf8");

    result2 += decipher.final("utf8")

    return result2;
}


const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

const print_menu = () => {
    console.log("");
    console.log("===============");
    console.log("how to use");
    console.log("encrypt [string]");
    console.log("decrypt [string]");
    console.log("compare [string] [cipher]");
    console.log("if you want to exit, type quit");
    console.log("===============");
    console.log("");
}

const menu = ["encrypt", "decrypt", "compare", "quit"];

print_menu();

rl.on("line", (line) => {
    let cmd = line.split(" ");
    
    let command = cmd[0];

    if (menu.indexOf(command) < 0) {
        console.log("there's no menu like that");
    } else {

        if (command === "quit") {
            rl.close();
        }

        if (!cmd[1]) {
            console.log("please write string to process");
        } else {
            
            if (command === "encrypt") {
                let input_str = cmd[1];
                let result = encrypt(input_str);

                console.log("encrypt [" + input_str + "] to [" + result + "]");

            } else if (command === "decrypt") {
                let cipher = cmd[1];
                let result = decrypt(cipher);

                console.log("decrypt [" + cipher + "] to [" + result + "]");

            } else if (command === "compare") {
                if (!cmd[2]) {
                    console.log("please write string to compare with");
                } else {
                    let input_str = cmd[1];
                    let cipher = cmd[2];

                    let result = (encrypt(input_str) === cipher) && (input_str === decrypt(cipher))
                    console.log("does two pharse mean same thing? : " + result);
                }
            }
        }
    }
})

rl.on("close", () => {
    console.log("bye :)");
    process.exit();
})