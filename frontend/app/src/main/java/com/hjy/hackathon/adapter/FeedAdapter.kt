package com.hjy.hackathon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hjy.hackathon.VO.FeedVO
import com.hjy.hackathon.viewHolder.FeedViewHolder
import com.hjy.hackathon.R
import com.bumptech.glide.Glide

class FeedAdapter(var data: ArrayList<FeedVO>, var context: Context) :
    RecyclerView.Adapter<FeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feed = data[position]

        holder.tv_id.text = feed.mb_id
        holder.tv_content.text = feed.board_title
        holder.tv_like.text = feed.board_like.toString()
        holder.tv_cost.text = feed.board_cost.toString()
        // 카테고리 TextView 해야함
        Glide.with(holder.itemView.context).load(feed.board_content).into(holder.img_content)
        Glide.with(holder.itemView.context).load(feed.board_like).into(holder.img_like) // 버튼으로 사용해야 함
        Glide.with(holder.itemView.context).load(feed.board_comment).into(holder.img_comment) // 버튼으로 사용해야 함


    }

}