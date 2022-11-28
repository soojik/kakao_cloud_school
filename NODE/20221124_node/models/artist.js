const Sequelize = require('sequelize');
const { sequelize } = require('.');

module.exports = class Artist extends Sequelize.Model{ 
    static init(sequelize) {
        return super.init({
            // 컬럼 설정
            id: {
                type:Sequelize.INTEGER.UNSIGNED,
                allowNull:false,
                unique:true,
                primaryKey:true
            },
            name:{
                type:Sequelize.STRING(50),
                allowNull:true
            },
            region:{
                type:Sequelize.STRING(50),
                allowNull:true
            },
            description:{
                type:Sequelize.STRING(200),
                allowNull:true
            },
            pictureurl:{
                type:Sequelize.STRING(200),
                allowNull:true
            },
            updatedate:{
                type:Sequelize.DATE,
                allowNull:true
            }
        }, {
            // 테이블 설정
            sequelize,
            timestamps:false,
            underscored:false,
            tableName:'artists',
            modelName:'artists',
            paranoid:false,
            charset:'utf8',
            collate:'utf8_general_ci'
        })
    }
}