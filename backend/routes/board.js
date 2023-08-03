const express = require("express");
const {v4:uuidv4} =require('uuid')
const fs = require('fs')
const db = require("../config/database");
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


// 게시글 작성 
router.post('/write', (req, res)=>{
    // 아이디, 날짜, 금액, 카테고리, 이미지, 내용
    let {mb_id, board_at, board_cost, board_cg, board_img, board_content} = JSON.parse(req.body.board)
    console.log(req.body.board)


    //img파일 디코딩(base64)
    let decode = Buffer.from(board_img, 'base64')
    const uuid = uuidv4() 
    fs.writeFileSync('public/img/board/'+ uuid +'.jpg', decode)

    let sql = "insert into tb_board (mb_id, board_at, board_cost, board_cg, board_img, board_content) value (?,?,?,?,?,?)"
    conn.query(sql, [mb_id, board_at, board_cost, board_cg, uuid, board_content], (err, rows)=>{
          if(err) {
           console.log(err)
           res.send('Fail')
          } else{
           res.send('Success')
          }
    })
   
})


module.exports = router;