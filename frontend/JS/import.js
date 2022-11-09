import {cube, foo, graph} from "./exportFiles/export.js";

graph.options = {
    color : "green",
    thickness : "3px"
}

graph.draw();

console.log(cube(4));

console.log(foo);