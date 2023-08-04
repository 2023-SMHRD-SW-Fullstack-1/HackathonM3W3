package com.hjy.hackathon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hjy.hackathon.R
import com.hjy.hackathon.onItemClickListener
import com.hjy.hackathon.vo.ChatRoomVO
import android.widget.TextView
import android.widget.ImageView



class ChatRoomAdapter(val context: Context, val chatroomList : ArrayList<ChatRoomVO>, private val onItemClickListener: onItemClickListener.OnItemClickListener)
    : RecyclerView.Adapter<ChatRoomAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View, private val onItemClickListener: onItemClickListener.OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val imgCRT: ImageView
        val tvCRTNick: TextView
        val tvCRTLastMsg: TextView
        val tvCRTLastMsgTime: TextView
        init {
            imgCRT = itemView.findViewById(R.id.imgCRT)
            tvCRTNick = itemView.findViewById(R.id.tvCRTNick)
            tvCRTLastMsg = itemView.findViewById(R.id.tvCRTLastMsg)
            tvCRTLastMsgTime = itemView.findViewById(R.id.tvCRTLastMsgTime)

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_room_temp, parent, false)
        return ViewHolder(view, onItemClickListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.tvCRTLastMsg.text = chatroomList[position].lastChatMsg
        holder.tvCRTNick.text = chatroomList[position].idx.toString()
        holder.tvCRTLastMsgTime.text = chatroomList[position].lastChatTime

    }

    override fun getItemCount(): Int {
        return chatroomList.size
    }



}