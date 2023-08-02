package com.hjy.hackathon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.hjy.hackathon.vo.ChatVO
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.hjy.hackathon.NewChatViewHolder
import com.hjy.hackathon.R

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
        holder.tvMsgOpp.setText(data[position].msg)
        holder.tvTimeOpp.setText(data[position].time)
        holder.tvMsgMy.setText(data[position].msg)
        holder.tvTimeMy.setText(data[position].time)
        // 나의 채팅과 상대방 채팅
        if(data[position].uid == id){
            holder.tvMsgOpp.visibility = View.GONE
            holder.tvTimeOpp.visibility = View.GONE
            holder.tvMsgMy.isVisible = true
            holder.tvTimeMy.isVisible = true
        } else {
            holder.tvMsgMy.visibility = View.GONE
            holder.tvTimeMy.visibility = View.GONE
//            holder.tvMsgOpp.isVisible = true
//            holder.tvTimeOpp.isVisible = true
        }

            }
    }

