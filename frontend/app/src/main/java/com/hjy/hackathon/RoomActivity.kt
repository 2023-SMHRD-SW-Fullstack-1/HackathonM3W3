package com.hjy.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hjy.hackathon.databinding.ActivityRoomBinding

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRoomBinding.inflate(layoutInflater);
        setContentView(binding.root);


    }
}