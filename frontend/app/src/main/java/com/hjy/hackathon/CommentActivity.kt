package com.hjy.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import com.hjy.hackathon.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCommentBinding.inflate(layoutInflater);

        setContentView(binding.root);

        val tvId = findViewById<TextView>(R.id.tv_id);
        tvId.text = ";;";
//        val container: ViewGroup = findViewById<ViewGroup>(R.id.feedContainer);

    }


}