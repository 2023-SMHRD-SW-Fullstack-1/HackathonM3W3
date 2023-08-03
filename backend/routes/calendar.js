const express = require('express')
const fs = require('fs')
const db = require("../config/database");
const router = express.Router()


const conn = db.init();
db.connect(conn);


router.post("/",(req, res)=>{

    let {date, id} = JSON.parse(req.body.Andmember)

    let sql = "SELECT board_at, SUM(board_cost) AS total_cost FROM tb_board WHERE board_at LIKE ? and mb_id = ? GROUP BY board_at order by board_at"

    conn.query(sql,[date.substr(0,4)+'%', id], (err,rows)=>{
        
        console.log(rows);

        if (err) {
            console.log(err);
            res.send("Fail");
        } else {
            res.send(rows);
        }
    })


})


router.post('/dayMoney', (req, res)=>{
    // 아이디, 날짜, 금액, 카테고리, 이미지, 내용
    var result =req.body.newDate
  
    var year = result.split("-")[0]
    var month = result.split("-")[1]
    var day = result.split("-")[2]
    month = parseInt(month)

    var dayday = year+"-"+month+"-"+ day;

    console.log(dayday);

//     let sql = "select board_cost from tb_board where board_at=?"

//     conn.query(sql,[dayday], (err,rows)=>{
        

       
        
   
// })

})

router.post("/day", (req, res)=>{
    let {date, id} = JSON.parse(req.body.calendar);

    let sql = "SELECT * FROM tb_board WHERE board_at = ? and mb_id = ?"

    conn.query(sql,[date, id], (err,rows)=>{
        
        console.log(rows);

        if (err) {
            console.log(err);
            res.send("Fail");
        } else {
            res.send(rows);
        }
    })
});




module.exports = router