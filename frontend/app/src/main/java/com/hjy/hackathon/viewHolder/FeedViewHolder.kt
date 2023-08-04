package com.hjy.hackathon.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hjy.hackathon.R

class FeedViewHolder(val itemView: View) : ViewHolder(itemView) {
    var tv_nick: TextView
    var tv_content: TextView
    var tv_cost: TextView
    var tv_category: TextView
    var img_content: ImageView
    var img_profile: ImageView
    var img_like: ImageView
    var img_comment: ImageView
    var tv_likeCnt: TextView

    init {
        tv_nick = itemView.findViewById(R.id.tv_id)
        tv_content = itemView.findViewById(R.id.tv_content)
        tv_cost = itemView.findViewById(R.id.tv_cost)
        tv_category = itemView.findViewById(R.id.tv_category)
        img_content = itemView.findViewById(R.id.img_content)
        img_profile = itemView.findViewById(R.id.img_profile)
        img_like = itemView.findViewById(R.id.img_like)
        img_comment = itemView.findViewById(R.id.img_comment)
        tv_likeCnt = itemView.findViewById(R.id.tv_likeCnt)
    }
}