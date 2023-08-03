package com.hjy.hackathon

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Base64
import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import com.hjy.hackathon.databinding.ActivityMainBinding

import com.hjy.hackathon.mainfragment.CalendarFragment
import com.hjy.hackathon.mainfragment.ChatListFragment
import com.hjy.hackathon.mainfragment.FeedFragment
import com.hjy.hackathon.vo.MemberVO


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val spf = getSharedPreferences("mySPF", AppCompatActivity.MODE_PRIVATE);
        val member = spf.getString("member", "");
        var memberVO = Gson().fromJson(member, MemberVO::class.java);
        val imageBytes = Base64.decode(memberVO.mb_profile, 0)
        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        binding.tvNickMain.text = memberVO.mb_id;
        binding.ivProfileMain.setImageBitmap(image);

        //MainAcitivty로 전환되자마자 CalenderFragement 화면 보여주기
        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            CalendarFragment()
        ).commit()


        binding.bnv.setOnItemSelectedListener{

           when(it.itemId){
               R.id.tab1 ->{ // 채팅
                   supportFragmentManager.beginTransaction().replace(
                       binding.fl.id,
                       ChatListFragment()
                   ).commit()

               }
               R.id.tab2 ->{ // 피드
                   supportFragmentManager.beginTransaction().replace(
                       binding.fl.id,
                       FeedFragment()
                   ).commit()
               }
               R.id.tab3 ->{ // 게시물등록
                   var intent = Intent(this@MainActivity, BoardActivity::class.java)
                   startActivity(intent)
               }
               R.id.tab4 ->{ // 마이페이지
                   supportFragmentManager.beginTransaction().replace(
                       binding.fl.id,
                       CalendarFragment()
                   ).commit()
               }
               R.id.tab5 ->{ // 회원정보수정


               }

           } //when문 끝
            true
        } //bnv 클릭 끝

    } // onCreate 끝

}