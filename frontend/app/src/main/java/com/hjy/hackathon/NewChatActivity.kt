package com.hjy.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.hjy.hackathon.adapter.ChatAdapter
import com.hjy.hackathon.utils.FBAuth
import com.hjy.hackathon.vo.ChatVO
import com.hjy.hackathon.vo.MemberVO

//채팅방 여기서 작업할게요~~
class NewChatActivity : AppCompatActivity() {

    lateinit var rvChat : RecyclerView
    lateinit var btnChatSend : Button
    lateinit var etChatMsg: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_chat)

        rvChat = findViewById(R.id.rvChat)
        btnChatSend = findViewById(R.id.btnChatSend)
        etChatMsg = findViewById(R.id.etChatMsg)

        val spf = getSharedPreferences("mySPF", MODE_PRIVATE);
        val member = spf.getString("member", "");
        var memberVO = Gson().fromJson(member, MemberVO::class.java);

        val database = Firebase.database
        val myRef = database.getReference("message")

        val data = ArrayList<ChatVO>()

        var adapter : ChatAdapter = ChatAdapter(applicationContext,R.layout.newchat_msg_temp, data, memberVO.mb_id);
        rvChat.layoutManager = LinearLayoutManager(applicationContext)
        rvChat.adapter=adapter

//        btnChatSend.setOnClickListener {
//            val fb = FBAuth;
//            Log.d("time", fb.getTime());
//            myRef.push().setValue(ChatVO(etChatMsg.text.toString(),"수진", fb.myTime(fb.getTime())))
//
//
//            etChatMsg.text.clear()
//        }

       myRef.addChildEventListener(ChatChildEvent(data,adapter, rvChat))
    }
}