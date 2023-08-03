package com.hjy.hackathon.mainfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hjy.hackathon.NewChatActivity
import com.hjy.hackathon.R
import com.hjy.hackathon.adapter.ChatRoomAdapter
import com.hjy.hackathon.onItemClickListener
import com.hjy.hackathon.vo.ChatRoomVO


class ChatListFragment : Fragment() {

    lateinit var rvChatRoom : RecyclerView
    lateinit var adapter: ChatRoomAdapter
    val chatList = ArrayList<ChatRoomVO>()
    val keyData = ArrayList<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_chat_list, container, false)
        // fregment_chat_list.xml 파일에 있는 recycleView 가져오기

        rvChatRoom = view.findViewById<RecyclerView>(R.id.rvChatRoom)
        chatList.add(ChatRoomVO("절약 꿀팁방", "익명아무개", "절약하는 꿀정보공유", "오전 9:11"))
        chatList.add(ChatRoomVO("채찍방(익명혼냄방)", "춘식이", "과소비,Flex 탈탈혼내드림", "오후 5:15"))
        chatList.add(ChatRoomVO("익명가계부", "루피", "이번달 수입지출", "오후 7:20"))
        Log.d("list", chatList.size.toString())
//        getChatRoomData()

         adapter = ChatRoomAdapter(requireActivity(), chatList , object :
             onItemClickListener.OnItemClickListener {
             override fun onItemClick(position: Int) {
                 var intent = Intent(requireActivity(), NewChatActivity::class.java)
                 intent.putExtra("roomId", chatList[position].uidOne);
                 startActivity(intent)
             }
         })

        rvChatRoom.layoutManager = LinearLayoutManager(requireActivity())
        rvChatRoom.adapter = adapter
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



