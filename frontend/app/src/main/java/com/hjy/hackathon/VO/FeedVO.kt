package com.hjy.hackathon.vo

import java.util.Date

data class FeedVO(
    var board_content: String?,
    var board_img: String?,
    var mb_nick : String?,
    var board_cost : Int?,
    var cg_type : String?

    // title, cg_idx 뺏음
)
