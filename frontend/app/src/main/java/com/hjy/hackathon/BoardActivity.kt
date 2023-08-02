package com.hjy.hackathon

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.databinding.ActivityBoardBinding
import com.hjy.hackathon.databinding.ActivityMainBinding
import com.hjy.hackathon.vo.BoardVO

class BoardActivity : AppCompatActivity() {

    lateinit var board_cost: EditText
    lateinit var board_category: ImageView
    lateinit var board_img: ImageView
    lateinit var board_content: EditText
    lateinit var reqQueue: RequestQueue


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        val binding = ActivityBoardBinding.inflate(layoutInflater);
        setContentView(binding.root);

        var btns = arrayOfNulls<Button>(15)
        var category = 0
        var categorys = intArrayOf(15)

        // img 1~ 16까지 카데고리 번호 부여
        for (index in 0 until 15) {

            var id = resources.getIdentifier("img" + (index + 1), "id", packageName)
            btns[index] = findViewById(id)
            categorys[index] = index + 1

            btns[index]!!.setOnClickListener {
                category = categorys[index]
            }
        } // for문 끝

        board_cost = findViewById(R.id.board_cost)
        board_img = findViewById(R.id.board_img)
        board_content = findViewById(R.id.board_memo)

        reqQueue = Volley.newRequestQueue(this@BoardActivity)

        binding.button.setOnClickListener {

            if (category == 0) {

            } else {


            }


        } //onCreate 끝

    }

}