const crypto = require("crypto");

const encrypt = password => {
    let method = "sha256";
    let encoded_with = "base64";
    let encrypted_password = crypto.createHash(method).update(password).digest(encoded_with);

    return encrypted_password;
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
    console.log("compare_password [string1] [string2]");
    console.log("if you want to exit, type quit");
    console.log("===============");
    console.log("");
}

print_menu();

rl.on("line" , (line) => {
    if (line === "quit") {
        rl.close();
    }

    let cmd = line.split(" ");
    command = cmd[0];

    if (command == "encrypt" && cmd.length == 2) {
        let password = cmd[1];

        console.log("...");

        let encrypted_password = encrypt(password);
        console.log("encrypt " + password + " with sha256, then encode with base64");
        console.log("result : " + encrypted_password);

    } else if (command == "compare_password" && cmd.length == 3) {
        let first_password = cmd[1];
        let second_password = cmd[2];

        let first_encrypted_password = encrypt(first_password);
        let second_encrypted_password = encrypt(second_password);

        console.log("are those two encrypted pharse have same result? : " + (first_encrypted_password === second_encrypted_password));
        console.log("result : " + first_encrypted_password + " and " + second_encrypted_password);
    } else {
        console.log("there's no menu like that");
        print_menu();
    }

})

rl.on("close", () => {
    console.log("bye :)");
    process.exit();
})