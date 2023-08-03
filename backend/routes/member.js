const express = require("express");
const {v4:uuidv4} = require("uuid");
const fs = require("fs");
const db = require("../config/database");
const router = express.Router();

const conn = db.init();
db.connect(conn);


router.post("/join", (req, res)=>{
    let {mb_id, mb_pw, mb_nick, mb_profile} = JSON.parse(req.body.member);

    let decode = Buffer.from(mb_profile, "base64");
    const uuid = uuidv4();
    fs.writeFileSync("public/img/member/" + uuid + ".jpg", decode);

    let sql = "insert into tb_member values (?, ?, ?, ?)";

    conn.query(sql, [mb_id, mb_pw, mb_nick, uuid], function(err, rows, fields){
        if (err) {
            console.log(err);
            res.send("Fail");
        } else {
            res.send("Success");
        }
    })
});



router.post("/login", (req, res)=>{
    console.log(req.body);
    let {mb_id, mb_pw} = JSON.parse(req.body.member);
    let sql = "select * from tb_member where mb_id = ? and mb_pw = ?";

    conn.query(sql, [mb_id, mb_pw], function(err, rows, fields){
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
});

router.post("/update", (req, res)=>{
    let {mb_id, mb_pw, mb_nick, mb_profile} = JSON.parse(req.body.member);

    let decode = Buffer.from(mb_profile, "base64");
    const uuid = uuidv4();
    fs.writeFileSync("public/img/member/" + uuid + ".jpg", decode);

    let sql = "update tb_member set mb_pw = ?, mb_nick = ?, mb_profile = ? where mb_id = ?";

    conn.query(sql, [mb_pw, mb_nick, uuid, mb_id], function(err, rows, fields){
        if (err) {
            console.log(err);
            res.send("Fail");
        } else {
            res.send("Success");
        }
    })
});


module.exports = router;