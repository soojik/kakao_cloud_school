const os = require("os");

if (os.type().toLocaleLowerCase().indexOf("windows") >= 0) {
    console.log("you're using Windows OS");
} else {
    console.log("where are we?");
}