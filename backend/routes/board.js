const express = require("express");
const db = require("../config/database");
const router = express.Router();

const conn = db.init();
db.connect(conn);

router.get("/feed", (req, res)=>{

    let sql = "select * from tb_board";

    conn.query(sql, function(err, rows, fields){
        let readFile = fs.readFileSync('public/img/member/'+rows[0].mb_profile+'.jpg'); //이미지 파일 읽기
        let encode = Buffer.from(readFile).toString('base64'); //파일 인코딩
        rows[0].mb_profile = encode;
        if (err) {
            console.log(err);
            res.send("Fail");
        } else {
            if(rows.length > 0) {
                res.send(rows[0]);
            } else {
                res.send("Fail");
            }
        }
    })
})


module.exports = router;