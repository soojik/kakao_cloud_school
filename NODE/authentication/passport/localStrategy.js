const passport = require('passport');
const localStrategy = require('passport-local').Strategy;
const bcrypt = require('bcrypt');
const user = require('../models/user');

module.exports = () => {
    passport.use(new localStrategy({
        // id에 해당하는 컬럼 이름
        usernameField: 'email',
        // 비밀번호에 해당하는 컬럼 이름
        passwordField: 'password'
    }, async (email, password, done) => {
        try {
            // 로그인 처리를 위해 email에 해당하는 데이터 찾기
            const exUser = await user.findOne({ where: { email } });

            if (exUser) {
                // 비밀번호 비교
                // 비밀번호는 해싱되어있기 때문에 bcrypt의 compare 함수를 이용해 비교
                const result = await bcrypt.compare(password, exUser.password);

                if (result) {
                    done(null, exUser);
                } else {
                    done(null, false, { message: "틀린 비밀번호" });
                }
            } else {
                done(null, false, { message: "없는 회원" });
            }
        } catch (err) {
            console.log(err);
            done(err);
        }
    }));
}