package com.hjy.hackathon.vo

data class FeedVO(
    var board_idx : Int,
    var board_content: String?,
    var board_at : String,
    var board_img: String?,
    var mb_nick : String?,
    var mb_id : String,
    var board_cost : Int?,
    var cg_type : String?,
    var mb_profile : String?

)
