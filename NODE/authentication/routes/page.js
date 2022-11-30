const express = require('express');
const { isLoggedIn, isNotLoggedIn } = require('./middlewares');

const router = express.Router();

// 공통된 처리 - 무조건 수행
router.use((req, res, next) => {
    // 로그인한 유저 정보
    // 유저 정보를 res.locals.user에 저장
    res.locals.user = req.user;

    // 게시글을 follow 하고/되고 있는 개수
    res.locals.followCount = 0;
    res.locals.follwingCount = 0;

    // 게시글을 follow 하고 있는 유저들의 목록
    res.locals.followerIdList = [];

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


router.get('/', (req, res, next) => {
    const twists = [];
    res.render('main', {
        title: 'NodeAuthentication',
        twists
    });
});

module.exports = router;