package com.hjy.hackathon

import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.hjy.hackathon.adapter.ChatAdapter
import com.hjy.hackathon.vo.ChatVO

class ChatChildEvent (var data : ArrayList<ChatVO>, var adapter: ChatAdapter, var rvChat : RecyclerView) : ChildEventListener{
    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        var temp : ChatVO? = snapshot.getValue(ChatVO::class.java)
        data.add(temp!!)
        adapter.notifyDataSetChanged()
//        rvChat.smoothScrollToPosition(data.size-1)
    }

    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

    }

    override fun onChildRemoved(snapshot: DataSnapshot) {
        TODO("Not yet implemented")
    }

    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        TODO("Not yet implemented")
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }

}