const foo = Math.PI * Math.SQRT2;

function cube(x) {
    return x * x * x;
}

var graph = {
    options : {
        color : "white",
        thickness : "2px"
    },
    draw:function() {
        console.log("Draw Function");
        console.log("color : " + this.options.color);
        console.log("thickness : " + this.options.thickness);
    }
}

export {foo, cube, graph};