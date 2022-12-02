const express = require('express');
const axios = require('axios');

// 매번 동일한 요청 위해 URL을 상수로 설정
// const URL = 'http://localhost:8000/v1';
const URL = 'http://localhost:8000/v2';

// ajax 요청을 할 때 누가 요청했는지 확인해주기 위해 orgin header 추가
axios.defaults.headers.origin = 'http://localhost:9000';

// 토큰 발급 코드
const request = async(req, api) => {
    try {
        if(!req.session.jwt) {
            const tokenResult = await axios.post(
                `${URL}/token`, {
                    clientSecret:process.env.CLIENT_SECRET
                }
            );

            req.session.jwt = tokenResult.data.token;

            // 토큰 발급 실패 때의 코드는 res 파라미터가 따로 없기 때문에 구현하지 않아도 될 듯 하다.
            const result = await axios.get(
                `${URL}${api}`, {
                    headers:{authorization:req.session.jwt}
                }
            )
            return result;
        }
    } catch (err) {

        // 419 error - 토큰 유효 기간 만료
        if (err.response.status === 419) {
            // 기존 토큰 삭제
            delete req.session.jwt;
            return request(req, api);
        }

        return err.response;
    }
}

const router = express.Router();

//
router.get('/mypost', async(req, res, next) => {
    try {
        const result = await request(req, '/posts/my');
        res.json(result.data);
    } catch(err) {
        console.error(err);
        next(err);
    }
})

// 토큰 테스트 라우터
router.get('/test', async (req, res, next) => {
    try {
        // 세션에 토큰이 없으면 토큰 발급 시도
        if (!req.session.jwt) {
            const tokenResult = await axios.post(`${URL}/token`, {
                clientSecret: process.env.CLIENT_SECRET,
            });
            // 토큰 발급 성공
            if (tokenResult.data && tokenResult.data.code === 200) {
                // 세션에 토큰 저장
                req.session.jwt = tokenResult.data.token;
            // 토큰 발급 실패
            } else {
                // 발급 실패 사유 응답
                return res.json(tokenResult.data);
            }
        }

        // 발급받은 토큰 테스트
        const result = await axios.get('http://localhost:8000/v1/test', {
            headers: { authorization: req.session.jwt },
        });
        return res.json(result.data);
    } catch (error) {
        console.error(error);
        
        // 토큰 만료 시
        if (error.response.status === 419) {
            return res.json(error.response.data);
        }
        return next(error);
    }
});

router.get('/', (req, res) => {
    res.render('main', {key : process.env.CLIENT_SECRET})
})
module.exports = router;
