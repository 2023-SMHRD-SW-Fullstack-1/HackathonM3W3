package com.hjy.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.hjy.hackathon.databinding.ActivityMainBinding
import com.hjy.hackathon.mainfragment.CalendarFragment
import com.hjy.hackathon.mainfragment.ChatListFragment
import com.hjy.hackathon.mainfragment.FeedFragment
import com.hjy.hackathon.mainfragment.MainFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);


        //MainAcitivty로 전환되자마자 MainFragement 화면 보여주기
        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            MainFragment()
        ).commit()


        binding.bnv.setOnItemSelectedListener{

           when(it.itemId){
               R.id.tab1 ->{ // 채팅
//                   var intent = Intent(this@MainActivity, NewChatActivity::class.java)
//                   startActivity(intent)
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
                       MainFragment()
                   ).commit()
               }
               R.id.tab5 ->{ // 회원정보수정
                   supportFragmentManager.beginTransaction().replace(
                       binding.fl.id,
                       CalendarFragment()
                   ).commit()

               }

           } //when문 끝
            true
        } //bnv 클릭 끝

    } // onCreate 끝

}