const express = require("express");
const db = require("../config/database");
const router = express.Router();

const conn = db.init();
db.connect(conn);

router.post("/rooms", (req, res)=>{
    let id = JSON.parse(req.body.member.id); // 아이디 받기
    let sql = "select * from tb_chatroom where in (select chat_idx from tb_chat where mb_id = ?) order by room_at";
    conn.query(sql, [id], function(err, rows, fields){
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
    });
});


module.exports = router;