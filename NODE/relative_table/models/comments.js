const Sequelize = require('sequelize');

module.exports = class Comment extends Sequelize.Model{
    static init(sequelize) {
        // 테이블에 대한 설정
        return super.init({
            // 컬럼에 대한 설정
            comment:{
                type:Sequelize.STRING(100),
                allowNull:false
            }
        }, {
            // 테이블에 대한 설정
            sequelize,
            // 생성, 수정날짜 자동으로 기록 created_at, updated_at
            timestamps:true,
            modelName:'Comment',
            tableName:'comment',
            paranoid:false,
            charset:'utf8',
            collate:'utf8_general_ci'
        })
    }

    static associate(db){
        // 외래키 즉, 관계에 대한 설정
        db.comment.belongsTo(db.user,
            {foreignKey:'commenter', targetKey:'id'});
    }
}