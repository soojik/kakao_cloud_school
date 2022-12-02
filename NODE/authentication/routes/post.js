const express = require('express');

const multer = require('multer');
const path = require('path');
const fs = require('fs');

// 데이터 삽입을 위한 모듈
const { post, hashtag } = require('../models');
const { isLoggedIn } = require('./middlewares');

const router = express.Router();

try {
    fs.readdirSync('public/img');
} catch (err) {
    fs.mkdirSync('public/img');
}

const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, cb) {
            cb(null, 'public/img/');
        },
        filename(req, file, cb) {
            const ext = path.extname(file.originalname);
            cb(null, path.basename(file.originalname, ext) + Date.now() + ext);
        }
    }), limits: { fileSize: 10 * 1024 * 1024 }
})

router.post('/img', isLoggedIn, upload.single('img'), (req, res) => {
    console.log(req.file);
    res.json({
        url:`/img/${req.file.filename}`
    })
});

const upload2 = multer();
router.post('/', isLoggedIn, upload2.none(), async(req, res, next) => {
    try{
        // 게시글 업로드
        const post_ = await post.create({
            content:req.body.content,
            img:req.body.url,
            UserId:req.user.id
        })
        // 해시태그 찾기 - # 으로 시작하는 모두 문자열 찾기
        const hashtags = req.body.content.match(/#[^\s#]*/g);
        // 해시태그가 있으면 삽입
        if (hashtags) {
            // 한꺼번에 실행
            const result = await Promise.all(
                // 배열의 전체 데이터 순서대로 대입해서 {} 안의 내용 수행
                hashtags.map(tag => {
                    return hashtag.findOrCreate({
                        where:{
                            title:tag.slice(1).toLowerCase()
                        }
                    })
                })
            );
            await post_.addHashtags(result.map(r => r[0]));
        }
        res.redirect('/');
    } catch(err) {
        console.log(err);
    }
})

module.exports = router;