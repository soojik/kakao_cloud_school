const Sequelize = require('sequelize');

module.exports = class Post extends Sequelize.Model {
    static init(sequelize) {
        return super.init({
            content: {
                type: Sequelize.STRING(140),
                allowNull: false
            },
            img: {
                type: Sequelize.STRING(200),
                allowNull: true
            }
        }, {
            sequelize,
            timestamps: true,
            underscored: false,
            modelName: 'Post',
            tableName: 'post',
            paranoid: false,
            charset: 'utf8',
            collate: 'utf8_general_ci'

        });
    }

    static associate(db) {
        // user 와는 1:N 관계
        db.post.belongsTo(db.user);

        // Hashtag 완느 N:M
        db.post.belongsToMany(db.hashtag, {
            through: 'post_hashtag'
        })
    }
}