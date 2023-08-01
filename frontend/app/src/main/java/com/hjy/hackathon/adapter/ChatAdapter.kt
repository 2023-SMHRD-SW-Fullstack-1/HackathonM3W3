package com.hjy.hackathon.adapter

import android.content.Context
import android.view.LayoutInflater
import com.hjy.hackathon.vo.ChatVO
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.core.view.isVisible
import com.hjy.hackathon.R
import com.hjy.hackathon.utils.FBAuth

class ChatAdapter (val context : Context, val chatList:ArrayList<ChatVO>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMsgOpp: TextView
        val tvTimeOpp: TextView
        val tvMsgMy: TextView
        val tvTimeMy: TextView

        init {
            tvMsgOpp = itemView.findViewById(R.id.tvMsgOpp)    // 상대방메세지
            tvTimeOpp = itemView.findViewById(R.id.tvTimeOpp)  // 상대방 메세지 시간
            tvMsgMy = itemView.findViewById(R.id.tvMsgMy)       // 나의 메세지
            tvTimeMy = itemView.findViewById(R.id.tvTimeMy)     // 내메세지 전송시간
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.newchat_msg_temp, null)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var time = FBAuth.myTime(chatList[position].time)

        if(chatList[position].uid == FBAuth.getUid()){
            holder.tvMsgMy.setText(chatList[position].msg)
            holder.tvTimeMy.setText(time)
            holder.tvMsgOpp.isVisible = false
            holder.tvTimeOpp.isVisible = false
            holder.tvMsgMy.isVisible = true
            holder.tvTimeMy.isVisible = true
        } else {
            holder.tvMsgOpp.setText(chatList[position].msg)
            holder.tvTimeOpp.setText(time)
            holder.tvMsgMy.isVisible = false
            holder.tvTimeMy.isVisible = false
            holder.tvMsgOpp.isVisible = true
            holder.tvTimeOpp.isVisible = true
        }
    }
}
