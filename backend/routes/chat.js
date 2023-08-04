const express = require("express");
const db = require("../config/database");

const router = express.Router();

const conn = db.init();
db.connect(conn);

router.get("/rooms/:id", (req, res)=>{
    let id = req.params.id;
    // let id = JSON.parse(req.body.member.id); // 아이디 받기
    let sql = "select * from tb_chatroom where room_idx in (select room_idx from tb_chat where mb_id = ?) order by room_at";
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

router.get("/start/:id", (req, res)=>{
    
    let ids = req.params.id.split("@@@");
    console.log(ids);
    let sql = "insert into tb_chatroom (room_content, room_at, room_title) values ('', '', '')";
    conn.query(sql, function(err, rows, fields){
        console.log(rows.insertId);
        let sql2 = "insert into tb_chat values (?, ?)";
        for (let i = 0; i < ids.length; i++){
            conn.query(sql2, [rows.insertId, ids[i]], function(err, rows, fields){
            })
        }
        res.send("" + rows.insertId);
    });

});

router.post("/update", (req, res)=>{
    let {msg, time, uid} = JSON.parse(req.body.chat);
    let roomId = parseInt(req.body.roomId);
    console.log(msg);
    console.log(time);
    console.log(uid);
    console.log(roomId);
    let sql = "update tb_chatroom set room_content = ?, room_at = ?, room_title = ? where room_idx = ?";
    conn.query(sql, [msg, time, uid, roomId], function(rows, err, fields){
        console.log(rows);
        res.send("Success");
    });
})


module.exports = router;