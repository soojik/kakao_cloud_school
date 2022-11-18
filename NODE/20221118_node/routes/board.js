const express = require("express");

const router = express.Router();

router.get("/", (req, res) => {
    res.send("welcome to board");
});

module.exports = router;