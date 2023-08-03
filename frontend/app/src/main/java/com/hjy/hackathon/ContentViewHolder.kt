package com.hjy.hackathon

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContentViewHolder(var itemView : View) : RecyclerView.ViewHolder(itemView) {

    var tv_title : TextView
    var tv_price : TextView
    var img : ImageView

    init {
        tv_title = itemView.findViewById(R.id.tv_title)
        tv_price = itemView.findViewById(R.id.tv_price)
        img = itemView.findViewById(R.id.img_content)
    }

}