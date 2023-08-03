package com.hjy.hackathon

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.databinding.ActivityBoardBinding
import com.hjy.hackathon.vo.BoardVO
import com.hjy.hackathon.vo.MemberVO
import java.io.ByteArrayOutputStream
import java.util.Date
import java.util.GregorianCalendar


//ProfileActivity 상속
open class BoardActivity : ProfileActivity() {


    // 바인딩
    var _binding: ActivityBoardBinding? = null;
    val binding get() = _binding!!;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityBoardBinding.inflate(layoutInflater);
        setContentView(binding.root);

        // 날짜선택
        val today = GregorianCalendar()
        val year: Int = today.get(Calendar.YEAR)
        val month: Int = today.get(Calendar.MONTH)
        val date: Int = today.get(Calendar.DATE)

        // 날짜 기본값 Today로 지정
        binding.boardDate.setText("${year}-${month + 1}-${date}")

        // 날짜선택 버튼
        binding.btnStartDate.setOnClickListener {

            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    binding.boardDate.setText("${year}-${month + 1}-${dayOfMonth}")
                }
            }, year, month, date)
            dlg.show()
        }


        // img버튼  1~ 16까지 카테고리 번호 부여
        val btns = arrayOfNulls<ImageButton>(15)
        val categorys = arrayOfNulls<TextView>(15)
        var category = 0

        for (index in 0 until 15) {

            val id = resources.getIdentifier("img" + (index + 1), "id", packageName)
            var categoryText = resources.getIdentifier("cg"+(index +1), "id",packageName)
            btns[index] = findViewById(id)
            categorys[index] = findViewById(categoryText)


            btns[index]!!.setOnClickListener {

                if(binding.boardCost.text.toString().equals("")){
                    Toast.makeText(applicationContext,"금액을 입력해주세요",Toast.LENGTH_SHORT).show()
                }else{
                    //인텐트로 날짜, 비용, 카데고리번호 넘기기
                    var myintent = Intent(this@BoardActivity, BoardSecondActivity::class.java)
                    myintent.putExtra("date", binding.boardDate.text.toString())
                    myintent.putExtra("cost", binding.boardCost.text.toString().toInt())
                    myintent.putExtra("category", categorys[index]!!.text.toString())
                    startActivity(myintent)
                }
            }

        } // for문 끝

    } //onCreate 끝

<<<<<<< HEAD
=======

    // 사진 불러오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setImageView(requestCode, data, binding.boardImg);


    }

>>>>>>> dcf78fc87afa648f0ecaca6c0e96a239dd739529
}



