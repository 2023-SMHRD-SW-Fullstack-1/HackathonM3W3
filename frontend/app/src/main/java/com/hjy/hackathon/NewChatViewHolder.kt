package com.hjy.hackathon

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hjy.hackathon.vo.ChatVO

class NewChatViewHolder (var itemView : View ) : ViewHolder(itemView){


    val tvMsgOpp: TextView
    val tvTimeOpp: TextView
    val tvMsgMy: TextView
    val tvTimeMy: TextView

    init {
        tvMsgOpp = itemView.findViewById(R.id.tvMsgOpp)    // 상대방메세지
        tvTimeOpp = itemView.findViewById(R.id.tvTimeOpp)  // 상대방 메세지 시간
        tvMsgMy = itemView.findViewById(R.id.tvMsgMy)       // 나의 메세지
        tvTimeMy = itemView.findViewById(R.id.tvTimeMy)     // 내메세지 전송시간
    }



}