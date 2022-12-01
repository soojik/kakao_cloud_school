const express = require('express');
const { isLoggedIn, isNotLoggedIn } = require('./middlewares');

const router = express.Router();

const { post, user, hashtag } = require('../models');

// 공통된 처리 - 무조건 수행
router.use((req, res, next) => {
    // 로그인한 유저 정보
    // 유저 정보를 res.locals.user에 저장
    res.locals.user = req.user;

    // 게시글을 follow 하고/되고 있는 개수
    res.locals.followerCount = req.user ? req.user.followers.length : 0;
    res.locals.followingCount = req.user ? req.user.followings.length : 0;

    // 게시글을 follow 하고 있는 유저들의 목록
    res.locals.followerIdList = req.user ? req.user.followings.map(f => f.id) : [];

    next();
});


router.get('/profile', isLoggedIn, (req, res) => {
    res.render('profile', {
        title: '내 정보 - NodeAuthentication'
    });
});

// 회원가입 - 로그인이 되어있지 않은 경우만 수행
router.get('/join', isNotLoggedIn, (req, res) => {
    res.render('join', {
        title: '회원가입 - NodeAuthentication'
    });
});



router.get('/', async (req, res, next) => {
    try {
        // post 모델의 모든 데이터를 찾아오는데
        // 이 때 user 정보의 id와 nick도 같이 가져온다.
        const posts = await post.findAll({
            include: {
                model: user,
                attributes: ['id', 'nick']
            },
            order: [['createdAt', 'DESC']]
        });

        res.render('main', {
            title: 'NodeAuthentication',
            twits: posts
        })

    } catch (err) {
        console.log(err);
        next(err);
    }
})

router.get('/hashtag', async (req, res, next) => {
    const query = req.query.hashtag;
    if (!query) {
        return res.redirect('/');
    }
    try {
        const hashtag_ = await hashtag.findOne({ where: { title: query } });
        let posts = [];
        if (hashtag_) {
            posts = await hashtag_.getPosts({ include: [{ model: user }] });
        }

        return res.render('main', {
            title: `${query} | NodeAuthentication`,
            twits: posts,
        });

    } catch (error) {
        console.error(error);
        return next(error);
    }
});


// router.get('/', (req, res, next) => {
//     const twists = [];
//     res.render('main', {
//         title: 'NodeAuthentication',
//         twists
//     });
// });

module.exports = router;