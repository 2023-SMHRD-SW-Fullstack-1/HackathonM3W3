const express = require('express')
const fs = require('fs')
const db = require("../config/database");
const router = express.Router()


const conn = db.init();
db.connect(conn);


router.post("/",(req, res)=>{
    
    // console.log(JSON.parse(req.body.Andmember));

    var result = JSON.parse(req.body.Andmember)
    let {date, id} = JSON.parse(req.body.Andmember)

    // var year = date.split("-")[0]
    // var month = date.split("-")[1]
    // month = parseInt(month)
    //console.log(year);
    //console.log(month);

    // var date2 = year + "-" + month
    // console.log("123" + date2);
    
    //let sql = "select board_cost, board_content, board_at from tb_board where board_at LIKE ? "
    //let sql = "select * from tb_board"
    let sql = "SELECT board_at, SUM(board_cost) AS total_cost FROM tb_board WHERE board_at LIKE ? and mb_id = ? GROUP BY board_at order by board_at"

    conn.query(sql,[date.substr(0,7)+'%', id], (err,rows)=>{
        
        console.log(rows);

        // var sum = 0;

        // for (const row of rows) {
        //     //console.log(row.board_cost);
        //     sum += row.board_cost
        // }
        
        //console.log(sum);
    
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




module.exports = router