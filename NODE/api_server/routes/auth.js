const express = require('express');

// 로그인 처리
const passport = require('passport');
// 회원 가입
const bcrypt = require('bcrypt');

const { isLoggedIn, isNotLoggedIn } = require('./middlewares');

const user = require('../models/user');

const router = express.Router();

// 회원가입 처리 - /auth/join인데 라우팅할 때 /auth 추가
// async 사용한 이유? - 
router.post('/join', isNotLoggedIn, async (req, res, next) => {
    // 데이터 찾아오기
    // req.body 에서 email, nick, password - 구조 분해 할당
    const { email, nick, password } = req.body;

    try {
        // exUser에 데이터가 삽입된다면, 이메일 중복
        const exUser = await user.findOne({ where: { email } });

        if (exUser) {
            // 회원 가입 페이지로 리다이렉트하는데 error 키에 메세지 가지고 이동
            return res.redirect('/join?error=exist');
        }
        // 비밀번호 해싱
        const hash = await bcrypt.hash(password, 12);
        // 저장
        await user.create({
            email, nick, password: hash
        });
        // 성공하면 메인페이지로 리다이렉트
        return res.redirect('/');

    } catch (err) {
        console.log(err);
        return next(err);
    }
});

// 로그인 처리
router.post('/login', isNotLoggedIn, async (req, res, next) => {

    console.log('login post');

    passport.authenticate('local', (authError, user, info) => {
        if (authError) {
            console.log(authError);
            return next(authError);
        }

        //일치하는 user 없을 때
        if (!user) {
            // ${} 는 템플릿 출력 문법이기때문에 백틱(`)으로 감싸주어야 한다.
            return res.redirect(`/?loginError=${info.message}`);
        }

        return req.login(user, (loginError) => {
            if (loginError) {
                console.log(loginError);
                return next(loginError);
            }

            return res.redirect('/');
        });
    })(req, res, next); // 미들웨어 내의 미들웨어에는 (req, res next) 를 붙여야한다.
});

// 로그아웃 처리
router.get('/logout', isLoggedIn, (req, res, next) => {
    req.logout((err) => {
        if (err) {
            return next(err);
        }
        // 세션 초기화
        req.session.destroy();
        res.redirect('/');
    })
})

// 카카오 로그인 눌렀을 때 처리
router.get('/kakao', passport.authenticate('kakao'));

// 카카오 로그인 실패했을 떄
router.get('/kakao/callback', passport.authenticate('kakao', {
    failureRedirect: '/'
}),
    (req, res) => {
        res.redirect('/');
    },
);

module.exports = router;