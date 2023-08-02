package com.hjy.hackathon.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hjy.hackathon.R

class FeedViewHolder(var itemView: View) : ViewHolder(itemView) {
    var tv_nick : TextView
    var tv_content : TextView
    var tv_cost : TextView
    var tv_category : TextView
    var img_content : ImageView


    init {
        tv_nick = itemView.findViewById(R.id.tv_id)
        tv_content = itemView.findViewById(R.id.tv_content)
        tv_cost = itemView.findViewById(R.id.tv_cost)
        tv_category = itemView.findViewById(R.id.tv_category)
        img_content = itemView.findViewById(R.id.img_content)
    }
}