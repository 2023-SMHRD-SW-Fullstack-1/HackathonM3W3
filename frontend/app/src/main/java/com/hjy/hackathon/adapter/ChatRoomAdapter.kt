package com.hjy.hackathon.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.hjy.hackathon.R
import com.hjy.hackathon.utils.FBAuth
import com.hjy.hackathon.utils.FBDatabase
import com.hjy.hackathon.vo.ChatRoomVO
import com.hjy.hackathon.vo.ChatVO


//어댑터생성
class ChatRoomAdapter (var context : Context ,var newchat_template: Int , var chatroomList : ArrayList<ChatRoomVO>)
    : RecyclerView.Adapter<ChatRoomAdapter.ViewHolder>(){

    interface onItemClickListener {
        fun  onItemClick(view: View, position:Int)
    }

    lateinit var monItemClickListener: onItemClickListener

    fun  setOnItemClickListener(onItemClickListener: onItemClickListener){
        monItemClickListener = onItemClickListener
    }

 // chatViewHolder 생성
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
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
                    monItemClickListener.onItemClick(itemView, position)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.newchat_msg_temp,null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatroomList.size
    }

    override fun onBindViewHolder(holder: ChatRoomAdapter.ViewHolder, position: Int) {
        var oppUid =chatroomList[position].uidOne
        if (chatroomList[position].uidOne == FBAuth.getUid())
            oppUid = chatroomList[position].uidTwo

         getUserNick(oppUid, holder.tvCRTNick, holder.imgCRT)


        holder.tvCRTLastMsg.setText(chatroomList[position].lastChatMsg)
       holder.tvCRTLastMsgTime.setText(FBAuth.myTime(chatroomList[position].lastChatTime))
        }
    }


//메소드 1
fun getImageData(context: Context, key : String, view: ImageView){
    val storageReference = Firebase.storage.reference.child("$key.png")

    storageReference.downloadUrl.addOnCompleteListener { task->
        if (task.isSuccessful){
            Glide.with(context)
                .load(task.result)
                .into(view)
        }
    }
}

//메소드 2
fun getUserNick(uid: String, tv: TextView, iv: ImageView){
    FBDatabase.database.getReference("member").child(uid).get().addOnSuccessListener {
        val item = it.getValue(ChatVO::class.java) as ChatVO
        tv.setText(item.uid)
        //getImageData(item.uid, iv)

       }.addOnFailureListener{
        Log.e("firebase", "Error getting data", it)
    }
}



