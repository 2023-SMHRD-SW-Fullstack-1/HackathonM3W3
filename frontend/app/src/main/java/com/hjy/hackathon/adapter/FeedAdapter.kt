package com.hjy.hackathon.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.hjy.hackathon.vo.FeedVO
import com.hjy.hackathon.viewHolder.FeedViewHolder
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.hjy.hackathon.CommentActivity
import com.hjy.hackathon.R
import com.hjy.hackathon.vo.MemberVO
import com.hjy.hackathon.vo.SerializableFeed
import java.io.Serializable

class FeedAdapter(var context: Activity, var template: Int, var data: ArrayList<FeedVO>) :
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

        if (feed.mb_profile != null) {
            val imageBytesProfile = Base64.decode(feed.mb_profile, 0);
            val imageProfile =
                BitmapFactory.decodeByteArray(imageBytesProfile, 0, imageBytesProfile.size);
            holder.img_profile.setImageBitmap(imageProfile);
        }

        if (feed.board_img != null) {
            val imageBytesContent = Base64.decode(feed.board_img, 0);
            val imageContent =
                BitmapFactory.decodeByteArray(imageBytesContent, 0, imageBytesContent.size);
            holder.img_content.setImageBitmap(imageContent);
        }

        holder.tv_nick.text = feed.mb_nick
        holder.tv_content.text = feed.board_content
        holder.tv_cost.text = feed.board_cost.toString()
        holder.tv_category.text = feed.board_cg
        holder.img_comment.setOnClickListener {
            val obj = SerializableFeed(
                feed.mb_id,
                feed.mb_profile,
                feed.board_img,
                feed.mb_nick,
                feed.board_content,
                feed.board_cost.toString(),
                feed.board_cg
            )

            var intent = Intent(context, CommentActivity::class.java);
            intent.putExtra("feed", obj);
            context.startActivity(intent);
        }


        var i = false;
        holder.img_like.setOnClickListener {
            if (i == true) {
                holder.img_like.setImageResource(R.drawable.feedunlike)
                i = false
            } else {
                holder.img_like.setImageResource(R.drawable.feedlike)
                i = true
            }
        }



//        Glide.with(holder.itemView.context).load(feed.board_img).into(holder.img_content)
//        var feedList : FeedVO = data.get(position)
//        holder.img_content.setImageResource(feedList.board_img!!)


    }


}