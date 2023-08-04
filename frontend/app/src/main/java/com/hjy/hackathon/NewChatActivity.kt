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
import com.hjy.hackathon.vo.ChatVO
import com.hjy.hackathon.vo.MemberVO
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

import com.google.firebase.auth.FirebaseAuth

//채팅방 여기서 작업할게요~~
class NewChatActivity : AppCompatActivity() {

    lateinit var rvChat : RecyclerView
    lateinit var btnChatSend : Button
    lateinit var etChatMsg: EditText
    private val auth = FirebaseAuth.getInstance() //추가

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_chat)


        rvChat = findViewById(R.id.rvChat)
        btnChatSend = findViewById(R.id.btnChatSend)
        etChatMsg = findViewById(R.id.etChatMsg)


        val spf = getSharedPreferences("mySPF", MODE_PRIVATE);

        val member = spf.getString("member", "");
        var memberVO = Gson().fromJson(member, MemberVO::class.java);


        val roomId = intent.getIntExtra("roomId", 0).toString();
        val database = Firebase.database
        val myRef = database.getReference(roomId!!)

        val data = ArrayList<ChatVO>()

        var adapter : ChatAdapter = ChatAdapter(applicationContext,R.layout.newchat_msg_temp, data, memberVO.mb_id);
        rvChat.layoutManager = LinearLayoutManager(applicationContext)
        rvChat.adapter=adapter

        btnChatSend.setOnClickListener {
            val  msg = etChatMsg.text.toString()

//FireBase RealTime DataBase의 Chat경로에 ChatVO class를 setvalue해줌 !
//            val chat = ChatVO(loginId,msg,myTime(getTime()))
//            myRef.push().setValue(chat)
            myRef.push().setValue(ChatVO(etChatMsg.text.toString(),memberVO.mb_id, myTime(getTime())))
            etChatMsg.text.clear()
        }

       myRef.addChildEventListener(ChatChildEvent(data,adapter, rvChat))
    }

    // id 가져오기
    private fun getCurrentUserId(): String {
        val user = auth.currentUser
        return user?.uid ?: "" // 사용자가 로그인하지 않은 경우 빈 문자열 반환
    }

    // 아이디 비교하기
    /**사용자uid와 targetUid 비교
     * @return Boolean*/

    fun checkUid(targetUid: String): Boolean {
        val userUid = getCurrentUserId()
        Log.d("check", "사용자uid: $userUid, targetUid: $targetUid")
        return targetUid == userUid
    }




    fun getTime(): String {
        // Calendar 객체는 getInstance() 메소드로 객체를 생성한다
        val currentTime = Calendar.getInstance().time
        // 시간을 나타낼 형식, 어느위치의 시간을 가져올건지 설정
        // "yyyy.MM.dd HH:mm:sss"

        val time = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREAN).format(currentTime)

        return time
    }


    /**시간을 오후 9:13같은 형식으로 바꿔준다*/
    fun myTime(time: String): String {
        var timeResult = ""
        if (time == "")
            return ""
        if (time.substring(11, 13).toInt() > 12)
            timeResult += "오후 " + (time.substring(11, 13).toInt() - 12) + time.substring(13, 16)
        else if (time.substring(11, 13).toInt() == 12)
            timeResult += "오후 " + time.substring(11, 16)
        else if (time.substring(11, 13).toInt() > 9)
            timeResult += "오전 " + time.substring(11, 16)
        else if (time.substring(11, 13).toInt() == 0)
            timeResult += "오전 12" + time.substring(13, 16)
        else
            timeResult += "오전 " + time.substring(12, 16)
        return timeResult
    }



}