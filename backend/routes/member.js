const express = require("express");
const {v4:uuidv4} = require("uuid");
const fs = require("fs");
const db = require("../config/database");
const router = express.Router();

const conn = db.init();
db.connect(conn);


router.post("/join", (req, res)=>{
    let {id, pw, nick, profile} = JSON.parse(req.body.Member);

    let decode = Buffer.from(profile, "base64");
    const uuid = uuidv4();
    fs.writeFileSync("public/img/board/" + uuid + ".jpg", decode);

    let sql = "insert into tb_member values (?, ?, ?, ?)";

    conn.query(sql, [id, pw, nick, profile], function(err, rows, fields){
        if (err) {
            console.log(err);
            res.send("Fail");
        } else {
            res.send("Success");
        }
    })
})



router.post("/login", (req, res)=>{
    let {id, pw} = JSON.parse(req.body.Member);
    let sql = "select * from tb_member where mb_id = ? and mb_pw = ?";

    conn.query(sql, [id, pw], function(err, rows, fields){
        console.log(rows);
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
});


module.exports = router;