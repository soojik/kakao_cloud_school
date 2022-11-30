const { STRING } = require('sequelize');
const Sequelize = require('sequelize');

module.exports = class User extends Sequelize.Model {
    static init(sequelize) {
        return super.init({
            // 컬럼에 대한 설정
            // 카카오 로그인을 고려한 컬럼 설정
            email: {
                type: Sequelize.STRING(40),
                allowNull: true,
                unique: true
            },
            nick: {
                type: Sequelize.STRING(40),
                allowNull: false
            },
            password: {
                // 비밀번호는 해싱한 후, 넣기 때문에 64의 배수로 정하면 된다.
                type: Sequelize.STRING(128),
                allowNull: true
            },
            provider: {
                type: Sequelize.STRING(10),
                allowNull: false,
                defaultValue: 'local'
            },
            snsId: {
                type: Sequelize.STRING(50),
                allowNull: true
            },
        }, {
            // 모델에 대한 설정
            sequelize,
            // 생성, 수정 날짜 저장
            timestamps: true,
            // 이름에 _ 넣을 것인지
            underscored: false,
            modelName: 'User',
            // 실제 만들어지는 table 이름
            tableName: 'snsuser',
            // 삭제에 관한
            paranoid: true,
            charset: 'utf8',
            collate: 'utf8_general_ci'
        });
    }

    static associate(db) {
        db.user.hasMany(db.post);
        db.user.belongsToMany(db.user, {
            foreignKey: 'followingId',
            as: 'followers',
            through: 'follow',
        });
        db.user.belongsToMany(db.user, {
            foreignKey: 'followerId',
            as: 'followings',
            through: 'follow'
        });
    }
}