package com.hjy.hackathon.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hjy.hackathon.R

class FeedViewHolder(var itemView: View) : ViewHolder(itemView) {
//    var img_profile: ImageView
    var tv_id : TextView
    var tv_content : TextView
    var img_content : ImageView
//    var img_category : ImageView
    var tv_like : TextView
    var img_comment : ImageView
    var tv_cost : TextView
    var tv_category : TextView
    var img_like : ImageView

    init {
//        img_profile = itemView.findViewById(R.id.img_profile)
        tv_id = itemView.findViewById(R.id.tv_id)
        tv_content = itemView.findViewById(R.id.tv_content)
        img_content = itemView.findViewById(R.id.img_content)
//        img_category = itemView.findViewById(R.id.img_category)
        tv_like = itemView.findViewById(R.id.tv_like)
        img_comment = itemView.findViewById(R.id.img_comment)
        tv_cost = itemView.findViewById(R.id.tv_cost)
        tv_category = itemView.findViewById(R.id.tv_category)
        img_like = itemView.findViewById(R.id.img_like)
    }
}