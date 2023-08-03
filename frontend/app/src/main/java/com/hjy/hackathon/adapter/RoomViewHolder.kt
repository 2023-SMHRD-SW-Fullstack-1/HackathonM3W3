package com.hjy.hackathon.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hjy.hackathon.R

class RoomViewHolder(var itemView : View) : ViewHolder(itemView) {

    var imgCRT : ImageView;
    var tvCRTNick : TextView;
    var tvCRTLastMsg : TextView;
    var tvCRTLastMsgTime : TextView;

    init {
        imgCRT = itemView.findViewById(R.id.imgCRT)
        tvCRTNick = itemView.findViewById(R.id.tvCRTNick)
        tvCRTLastMsg = itemView.findViewById(R.id.tvCRTLastMsg)
        tvCRTLastMsgTime = itemView.findViewById(R.id.tvCRTLastMsgTime)
    }
}