const Sequelize = require('sequelize');

module.exports = class User extends Sequelize.Model{
    static init(sequelize) {
        // 테이블에 대한 설정
        return super.init({
            // 컬럼에 대한 설정
            name:{
                type:Sequelize.STRING(20),
                allowNull:false,
                unique:true
            },
            age:{
                type:Sequelize.INTEGER,
                allowNull:false
            }
        }, {
            // 테이블에 대한 설정
            sequelize,
            // 생성, 수정날짜 자동으로 기록 created_at, updated_at
            timestamps:true,
            modelName:'User',
            tableName:'user',
            paranoid:false,
            charset:'utf8',
            collate:'utf8_general_ci'
        })
    }

    static associate(db){
        // 외래키 즉, 관계에 대한 설정
        // 내 id와 commenter 를 연동시키되, 1:n 으로 hasMany
        db.user.hasMany(db.comment, {foreignKey:'commenter', sourceKey:'id'});
    }
}