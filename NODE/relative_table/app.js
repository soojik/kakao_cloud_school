const express = require('express');
const path = require('path');

const app = express();
app.set('port', process.env.PORT || 8000);

const {sequelize} = require('./models');
sequelize.sync({force:false})
    .then(() => {
        console.log("데이터베이스 연결 성공");
    })
    .catch((err) => {
        console.log(err);
    });

app.listen(app.get('port'),
    console.log(app.get('port'), "번 포트 대기중 ... "));