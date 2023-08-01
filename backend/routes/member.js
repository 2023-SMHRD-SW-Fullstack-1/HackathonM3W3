const express = require("express");
const db = require("../config/database");
const router = express.Router();

const conn = db.init();
db.connect(conn);

router.post("/login", (req, res)=>{
    let {id, pw} = JSON.parse(req.body.AndMember);
    let sql = "select * from andmember where id = ? and pw = ?";

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
})


module.exports = router;