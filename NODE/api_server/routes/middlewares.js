// 로그인 여부 판단
exports.isLoggedIn = (req, res, next) => {
  if (req.isAuthenticated()) {
    next();
  } else {
    // 메세지를 생성하는 query string(parameter)로 사용할 것이라서 encoding 해주어야 한다.
    const message = encodeURIComponent('로그인 필요');
    // 이전 request 객체의 내용을 모두 삭제하고 새로운 요청 흐름을 만드는 것으로
    // 새로 고침하면 결과 화면만 새로고침 된다.
    res.redirect(`/?error=${message}`);
  }
}

exports.isNotLoggedIn = (req, res, next) => {
  //로그인 되어 있지 않았다면 다음으로 넘어가고 그렇지 않으면 리다이렉트
  if (!req.isAuthenticated()) {
    next();
  } else {
    const message = encodeURIComponent('로그인한 상태입니다.');
    res.redirect(`/?error=${message}`);
  }
};

const jwt = require('jsonwebtoken');

exports.verifyToken = (req, res, next) => {
  try {
    req.decoded = jwt.verify(req.headers.authorization, process.env.JWT_SECRET);
    return next();
  } catch(err) {
    if (err.name === 'TokenExpiredError') {
      return res.status(419).json({
        code:419,
        message:'토큰이 만료되었습니다.'
      });
    }
    return res.status(401).json({
      // 401 error - 권한이 없음을 나타내는 에러 코드
      code:401,
      message:'유효하지 않은 토큰이다.'
    });
  }
}

const RateLimit = require('express-rate-limit');

exports.apiLimiter = RateLimit({
  windowMs: 60 * 1000, // 1분
  max: 10,
  delayMs: 0,
  handler(req, res) {
    res.status(this.statusCode).json({
      code: this.statusCode, // 기본값 429
      message: '1분에 한 번만 요청할 수 있습니다.',
    });
  },
});

exports.deprecated = (req, res) => {
  res.status(410).json({
    code: 410,
    message: '새로운 버전이 나왔습니다. 새로운 버전을 사용하세요.',
  });
};