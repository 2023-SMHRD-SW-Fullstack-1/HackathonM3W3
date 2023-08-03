package com.hjy.hackathon.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hjy.hackathon.vo.FeedVO
import com.hjy.hackathon.viewHolder.FeedViewHolder
import com.bumptech.glide.Glide

class FeedAdapter(var context: Context, var template: Int, var data: ArrayList<FeedVO>) :
    RecyclerView.Adapter<FeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {

        var template_view: View = LayoutInflater.from(context).inflate(template, parent, false)

        var VH: FeedViewHolder = FeedViewHolder(template_view)

        return VH

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feed = data[position]

        if (feed.mb_profile != null){
            val imageBytesProfile = Base64.decode(feed.mb_profile, 0);
            val imageProfile = BitmapFactory.decodeByteArray(imageBytesProfile, 0, imageBytesProfile.size);
            holder.img_profile.setImageBitmap(imageProfile);
        }

        if (feed.board_img != null) {
            val imageBytesContent = Base64.decode(feed.board_img, 0);
            val imageContent = BitmapFactory.decodeByteArray(imageBytesContent, 0, imageBytesContent.size);
            holder.img_content.setImageBitmap(imageContent);
        }

        holder.tv_nick.text = feed.mb_nick
        holder.tv_content.text = feed.board_content
        holder.tv_cost.text = feed.board_cost.toString()
        holder.tv_category.text = feed.board_cg

//        Glide.with(holder.itemView.context).load(feed.board_img).into(holder.img_content)
//        var feedList : FeedVO = data.get(position)
//        holder.img_content.setImageResource(feedList.board_img!!)


    }

}