package com.hjy.hackathon.mainfragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.NewChatActivity
import com.hjy.hackathon.R
import com.hjy.hackathon.adapter.ChatRoomAdapter
import com.hjy.hackathon.onItemClickListener
import com.hjy.hackathon.vo.ChatRoomVO
import com.hjy.hackathon.vo.MemberVO
import org.json.JSONArray


class ChatListFragment : Fragment() {

    lateinit var rvChatRoom : RecyclerView
    lateinit var adapter: ChatRoomAdapter
    val chatList = ArrayList<ChatRoomVO>()
    val keyData = ArrayList<String>()

    lateinit var reqQueue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_chat_list, container, false)
        // fregment_chat_list.xml 파일에 있는 recycleView 가져오기
        reqQueue = Volley.newRequestQueue(requireActivity());

        val spf = requireActivity().getSharedPreferences("mySPF", Context.MODE_PRIVATE)
        val strMember = spf.getString("member", "기본값")
        val memberVO = Gson().fromJson(strMember, MemberVO::class.java)
        val id = memberVO.mb_id

        rvChatRoom = view.findViewById<RecyclerView>(R.id.rvChatRoom)

        Log.d("id", id.toString());
        val request = object : StringRequest(
            Request.Method.GET,
            "http://172.30.1.9:8888/chat/rooms/" + id,
            {
                response ->
                Log.d("response", response.toString())
                if (response != "Fail"){
                    val result = JSONArray(response);
                    for (i in 0 until result.length()) {
                        val room = result.getJSONObject(i)
                        val room2 = ChatRoomVO(room.getInt("room_idx"), room.getString("room_title"), room.getString("room_content"), room.getString("room_at"));
                        Log.d("room",room2.toString());
                        chatList.add(room2);

                        adapter = ChatRoomAdapter(requireActivity(), chatList , object :
                            onItemClickListener.OnItemClickListener {
                            override fun onItemClick(position: Int) {
                                var intent = Intent(requireActivity(), NewChatActivity::class.java)
                                Log.d("idx", chatList[position].idx.toString());
                                intent.putExtra("roomId", chatList[position].idx);
                                startActivity(intent)
                            }
                        })

                        rvChatRoom.layoutManager = LinearLayoutManager(requireActivity())
                        rvChatRoom.adapter = adapter
                }
                }
            },
            {
                error ->
                Log.d("error", error.toString())
            }
        ){}

        reqQueue.add(request);




        return view
    }

}












//        adapter.setOnItemClickListener(object : ChatRoomAdapter.onItemClickListener{
//            override fun onItemClick(view: View, position: Int) {
//                val intent = Intent(requireContext(),ChatActivity::class.java)
//                var oppUid = chatList[position].uidOne
//                if (oppUid == spf)
//                    oppUid = chatList[position].uidTwo
//                intent.putExtra("oppUid", oppUid)
//                intent.putExtra("chatroomKey", keyData[position])
//
//                startActivity(intent)
//            }
//        })




//    fun getChatRoomData(){
//        val postListener = object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                chatList.clear()
//                // firebase에서 snapshot으로 데이터를 받아온 경우
//                for(model in snapshot.children) {
//                    val item = model.getValue(ChatRoomVO::class.java) as ChatRoomVO
//                    if(item.uidOne == FBAuth.getUid() || item.uidTwo == FBAuth.getUid()) {
//                        chatList.add(item)
//                        keyData.add(model.key.toString())
//                    }
//                }
//                adapter.notifyDataSetChanged()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // 오류가 발생했을 때 실행되는 함수
//            }
//        }
//        FBDatabase.database.getReference("chatroom").addValueEventListener(postListener)
//    }



