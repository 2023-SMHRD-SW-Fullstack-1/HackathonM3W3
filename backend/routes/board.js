const express = require("express");
const db = require("../config/database");
const fs = require("fs");
const router = express.Router();

const conn = db.init();
db.connect(conn);

router.post("/feed", (req, res)=>{

    let sql = "select A.*, B.mb_nick, B.mb_profile from tb_board as A inner join tb_member as B on A.mb_id = B.mb_id";

    conn.query(sql, function(err, rows, fields){
        for (let i = 0; i < rows.length; i++){
            let readFileP = fs.readFileSync('public/img/member/'+rows[i].mb_profile+'.jpg');
            let encodeP = Buffer.from(readFileP).toString('base64');
            rows[i].mb_profile = encodeP;

            let readFileB = fs.readFileSync('public/img/member/'+rows[i].board_img+'.jpg');
            let encodeB = Buffer.from(readFileB).toString('base64');
            rows[i].board_img = encodeB;
        }
        
        if (err) {
            console.log(err);
            res.send("Fail");
        } else {
            if(rows.length > 0) {
                res.send(rows);
            } else {
                res.send("Fail");
            }
        }
    })
})


module.exports = router;