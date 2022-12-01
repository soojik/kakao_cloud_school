const passport = require('passport');

// 카카오 passport
const kakaoStrategy = require('passport-kakao').Strategy;

const user = require('../models/user');

module.exports = () => {
    passport.use(new kakaoStrategy(
        {
            clientID: process.env.KAKAO_ID,
            callbackURL: '/auth/kakao/callback'
        },
        /*
              * clientID에 카카오 앱 아이디 추가
              * callbackURL: 카카오 로그인 후 카카오가 결과를 전송해줄 URL
              * accessToken, refreshToken: 로그인 성공 후 카카오가 보내준 토큰
              * profile: 카카오가 보내준 유저 정보. profile의 정보를 바탕으로 회원가입
        */
        async (accessToken, refreshToken, profile, done) => {
            console.log('kakao profile', profile);
            try {
                // 이전에 로그인한 적 있는지 찾기 위해
                // 카카오 아이디와 provider 가 kakao로 되어있는
                // 데이터가 있는지 조회
                const exUser = await user.findOne({
                    where: { snsId: profile.id, provider: 'kakao' }
                });

                if (exUser) {
                    done(null, exUser);
                } else {
                    const newUser = await user.create({
                        email: profile._json.kakao_account.email,
                        nick: profile.displayName,
                        snsId: profile.id,
                        provider: 'kakao'
                    });
                    done(null, newUser);
                }
            } catch (err) {
                console.log(err);
                done(err);
            }
        }))
}