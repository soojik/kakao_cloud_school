const express = require('express');
const { isLoggedIn } = require('./middlewares');
const user = require('../models/user');

const router = express.Router();

router.post('/:id/follow', isLoggedIn, async (req, res, next) => {
  try {
    const user_ = await user.findOne({ where: { id: req.user.id } });
    if (user_) {
      await user_.addFollowing(parseInt(req.params.id, 10));
      res.send('success');
    } else {
      res.status(404).send('no user');
    }
  } catch (error) {
    console.error(error);
    next(error);
  }
});
module.exports = router;