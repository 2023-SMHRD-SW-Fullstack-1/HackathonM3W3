package com.hjy.hackathon.vo

import java.util.Date

data class FeedVO(
    var board_idx: Int,
    var board_title: String?,
    var board_content: String?,
    var board_at: Date?,
    var board_img: String?,
    var mb_id : String?,
    var board_cost : Int?,
    var cg_idx : Int?,
    var board_like : Int?,
    var board_comment : String?,
)
