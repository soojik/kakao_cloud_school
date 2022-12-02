const express = require('express');
const jwt = require('jsonwebtoken');
const { verifyToken } = require('./middlewares');
const { domain, user, post, hashtag } = require('../models');

const router = express.Router();


// token의 유무를 확인하는 작업에는 verifyToken으로 확인
router.get('/test', verifyToken, (req, res) => {
    res.json(req.decoded);
})

router.post('/token', async (req, res) => {
    const { clientSecret } = req.body;
    try {
        const domain_ = await domain.findOne({
            where: { clientSecret },
            include: {
                model: user,
                attribute: ['nick', 'id'],
            },
        });
        if (!domain_) {
            return res.status(401).json({
                code: 401,
                message: '등록되지 않은 도메인입니다. 먼저 도메인을 등록하세요',
            });
        } const token = jwt.sign({
            id: domain_.User.id,
            nick: domain_.User.nick,
        }, process.env.JWT_SECRET, {
            expiresIn: '1m', // 1분
            issuer: 'nodebird',
        });
        return res.json({
            code: 200,
            message: '토큰이 발급되었습니다',
            token,
        });
    } catch (error) {
        console.error(error);
        return res.status(500).json({
            code: 500,
            message: '서버 에러',
        });
    }
});

// token의 유무를 확인하는 작업에는 verifyToken으로 확인
router.get('/posts/my', verifyToken, (req, res) => {
    post.findAll({
        where: { userId: req.decoded.id }      
    })
    .then((posts) => {
        console.log(posts);
        res.json({ code: 200, payload: posts });
    })
    .catch((err) => {
        console.log(err);
        return res.status(500).json({
            code: 500,
            message: '서버 에러'
        })
    })
})

module.exports = router;