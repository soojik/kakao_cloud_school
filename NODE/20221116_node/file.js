const fs = require("fs");

let data = fs.readFileSync("./test.txt")

let arr = data.toString().split("\n");

arr.forEach((e) => {
    console.log("read file with sync");
    let data = e.split(":");
    console.log("===============");
    console.log("name : " + data[0]);
    console.log("species : " + data[1]);
    console.log("===============");
})

fs.readFile("./test_.txt", (error, data) => {
    console.log("read file without sync");
    if (error) {
        console.log(error.message);
    } else {
        console.log(data.toString().split("\n"));
    }
})

const fs_promises = require("fs").promises;
console.log("read file without sync ver.2");
fs_promises.readFile("./test_.txt")
    .then((data) => {console.log(data.toString().split("\n"));})
    .catch((error) => {console.log(error.message)});

const readStream = fs.createReadStream('./test.txt', { highWaterMark: 16 });
data = [];

readStream.on('data', (chunk) => {
    data.push(chunk);
});

readStream.on('end', () => {
    console.log("read file in buffer");

    let result = Buffer.concat(data);
    split_result = result.toString().split("\n");

    split_result.forEach((e) => {
        let data = e.split(":");
        console.log("===============");
        console.log("name : " + data[0]);
        console.log("species : " + data[1]);
        console.log("===============");
    })

});

readStream.on('error', (err) => {
    
});

