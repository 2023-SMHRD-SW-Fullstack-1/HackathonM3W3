package com.hjy.hackathon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.hjy.hackathon.vo.ChatVO
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.hjy.hackathon.viewholder.NewChatViewHolder

import androidx.recyclerview.widget.RecyclerView.Adapter

class ChatAdapter (val context : Context, var template : Int , val data:ArrayList<ChatVO>, var id : String) : Adapter<NewChatViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewChatViewHolder {

        var template_View : View = LayoutInflater.from(parent.context).inflate(template,parent,false)
        var VH = NewChatViewHolder(template_View)
        return  VH
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NewChatViewHolder, position: Int) {

//        var time = FBAuth.myTime(data[position].time)

        if(data[position].uid == id){
            holder.tvMsgMy.setText(data[position].msg)
            holder.tvTimeMy.setText(data[position].time)
            holder.tvMsgOpp.isVisible = false
            holder.tvTimeOpp.isVisible = false
            holder.tvMsgMy.isVisible = true
            holder.tvTimeMy.isVisible = true
        } else {
            holder.tvMsgOpp.setText(data[position].msg)
            holder.tvTimeOpp.setText(data[position].time)
            holder.tvMsgMy.isVisible = false
            holder.tvTimeMy.isVisible = false
            holder.tvMsgOpp.isVisible = true
            holder.tvTimeOpp.isVisible = true
        }
    }
}
