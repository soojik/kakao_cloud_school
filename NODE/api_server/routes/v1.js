const express = require('express');
const jwt = require('jsonwebtoken');
const { verifyToken } = require('./middlewares');
const { domain, uwer } = require('../models');

const router = express.Router();

// token의 유무를 확인하는 작업에는 verifyToken으로 확인
router.get('/test', verifyToken, (req, res) => {
    res.json(req.decoded);
})

module.exports = router;