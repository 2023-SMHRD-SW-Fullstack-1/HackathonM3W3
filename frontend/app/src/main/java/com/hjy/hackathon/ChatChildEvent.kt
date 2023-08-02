package com.hjy.hackathon

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.hjy.hackathon.adapter.ChatAdapter
import com.hjy.hackathon.vo.ChatVO

class ChatChildEvent (var data : ArrayList<ChatVO>, var adapter: ChatAdapter) : ChildEventListener{
    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        var temp : ChatVO? = snapshot.getValue(ChatVO::class.java)
        data.add(temp!!)
        adapter.notifyDataSetChanged()
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