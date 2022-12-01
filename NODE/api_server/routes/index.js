const express = require('express');
const { v4: uuidv4 } = require('uuid');
const { user, domain } = require('../models');
const { isLoggedIn } = require('./middlewares');

const router = express.Router();

router.get('/', async (req, res, next) => {
    try {
        const user_ = await user.findOne({
            where: { id: req.user && req.user.id || null },
            include: { model: domain },
        });
        res.render('login', {
            user_,
            domains: user_ && user_.Domains,
        });
    } catch (err) {
        console.error(err);
        next(err);
    }
});

// 도에인 등록 처리
router.post('/domain', isLoggedIn, async (req, res, next) => {
    try {
        await domain.create({
            userId: req.user.id,
            host: req.body.host,
            type: req.body.type,
            clientSecret: uuidv4(),
        });
        res.redirect('/');
    } catch (err) {
        console.error(err);
        next(err);
    }
});

module.exports = router;
