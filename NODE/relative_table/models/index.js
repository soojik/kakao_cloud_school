const Sequelize = require('sequelize');

const user = require('./users');
const comment = require('./comments');

const env = process.env.NODE_DEV || 'development';
const config = require('../config/config.json')[env];

const db = {};

const sequelize = new Sequelize(config.database, config.username, config.password, config);

db.Sequelize = Sequelize;
db.user = user;
db.comment = comment;

user.init(sequelize);
comment.init(sequelize);

user.associate(db);
comment.associate(db);

db.sequelize = sequelize;

module.exports = db;