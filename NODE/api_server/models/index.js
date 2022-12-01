const Sequelize = require('sequelize');

const env = process.env.NODE_ENV || 'development';
const config = require('../config/config.json')[env];
const db = {};

const user = require('./user');
const post = require('./post');
const hashtag = require('./hashtag');
const domain = require('./domain');

const sequelize = new Sequelize(config.database, config.username, config.password, config);

db.sequelize = sequelize;
db.Sequelize = Sequelize;

db.user = user;
db.post = post;
db.hashtag = hashtag;
db.domain = domain;

// 초기화 작업
user.init(sequelize);
post.init(sequelize);
hashtag.init(sequelize);
domain.init(sequelize);

// 관계 설정
user.associate(db);
post.associate(db);
hashtag.associate(db);
domain.associate(db);

module.exports = db;