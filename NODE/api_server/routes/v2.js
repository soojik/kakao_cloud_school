const express = require('express');
const jwt = require('jsonwebtoken');

const { verifyToken, apiLimiter, deprecated } = require('./middlewares');
const { domain, user, post, hashtag } = require('../models');

const router = express.Router();

// CORS 정책 설정
const cors = require('cors');
const url = require('url');
// router.use(async(req, res, next) => {
//     const domain_ = await domain.findOne({
//         where:{host:url.parse(req.get('origin')).host}
//     });

//     if (domain_) {
//         cors({
//             origin:req.get('origin'),
//             credentials:true
//         })(req, res, next);
//     } else {
//         next();
//     }
// })

// 무조건 CORS 허용
router.use(cors({
    credentials:true
}));

// 모든 라우팅 처리에서 deprecated 적용
router.use(deprecated);

router.post('/token', apiLimiter, async (req, res) => {
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
        }
        const token = jwt.sign({
            id: domain_.User.id,
            nick: domain_.User.nick,
        }, process.env.JWT_SECRET, {
            expiresIn: '30m', // 30분
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

router.get('/test', verifyToken, apiLimiter, (req, res) => {
    res.json(req.decoded);
});

router.get('/posts/my', apiLimiter, verifyToken, (req, res) => {
    post.findAll({ where: { userId: req.decoded.id } })
        .then((posts) => {
            console.log(posts);
            res.json({
                code: 200,
                payload: posts,
            });
        })
        .catch((error) => {
            console.error(error);
            return res.status(500).json({
                code: 500,
                message: '서버 에러',
            });
        });
});
router.get('/posts/hashtag/:title', verifyToken, apiLimiter, async (req, res) => {
    try {
        const hashtag = await hashtag.findOne({ where: { title: req.params.title } });
        if (!hashtag) {
            return res.status(404).json({
                code: 404,
                message: '검색 결과가 없습니다',
            });
        }
        const posts = await hashtag.getPosts();
        return res.json({
            code: 200,
            payload: posts,
        });
    } catch (error) {
        console.error(error);
        return res.status(500).json({
            code: 500,
            message: '서버 에러',
        });
    }
});

module.exports = router;