package com.hjy.hackathon.vo
// 아이디, 날짜, 금액, 카테고리, 이미지, 내용
data class BoardVO (var mb_id:String, var board_at :String?, var board_cost : Int , var board_cg: String?, var board_img : String?, var board_content : String?) {
}