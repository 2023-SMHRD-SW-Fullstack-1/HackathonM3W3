package com.hjy.hackathon

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.databinding.ActivityMainBinding
import com.hjy.hackathon.mainfragment.CalendarFragment
import com.hjy.hackathon.mainfragment.ChatListFragment
import com.hjy.hackathon.mainfragment.FeedFragment
import com.hjy.hackathon.vo.MemberVO


class MainActivity : AppCompatActivity() {

    lateinit var member : String;

    lateinit var reqQueue : RequestQueue;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        reqQueue = Volley.newRequestQueue(this);

        var calendar = CalendarFragment()

        val spf = getSharedPreferences("mySPF", AppCompatActivity.MODE_PRIVATE);
        val member0 = spf.getString("member", "")!!;
        var memberVO0 = Gson().fromJson(member0, MemberVO::class.java);
        val memberVO1 : MemberVO? = null;

        val other = intent.getStringExtra("other");
        if (other != null) {
            val bundle = Bundle();
            bundle.putString("other", other);
            calendar.arguments= bundle;
            binding.bnv.visibility = View.GONE;

            val request = object : StringRequest(
                Request.Method.POST,
                "http://172.30.1.28:8888/member",
                {
                        response ->
                        Log.d("멤버", response.toString());
                        member = response;
                        var memberVO1 = Gson().fromJson(member, MemberVO::class.java);
                        val imageBytes = Base64.decode(memberVO1.mb_profile, 0)
                        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                        binding.tvNickMain.text = memberVO1.mb_id;
                        binding.ivProfileMain.setImageBitmap(image);
                },
                {
                        error ->
                    Log.d("error", error.toString());
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val params : MutableMap<String, String> = HashMap<String, String>();

                    val member2 = MemberVO(other, null, null, null);
                    params.put("member", Gson().toJson(member2));
                    return params;
                }
            }

            reqQueue.add(request);
        } else {

            val imageBytes = Base64.decode(memberVO0.mb_profile, 0)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            binding.tvNickMain.text = memberVO0.mb_id;
            binding.ivProfileMain.setImageBitmap(image);
            binding.btnChatStart.visibility = View.INVISIBLE;
        }

        binding.btnChatStart.setOnClickListener {
            val request = object : StringRequest(
                Request.Method.GET,
                "http://172.30.1.28:8888/chat/start/" + memberVO0.mb_id + "@@@" + other,
                {
                        response ->
                    var intent = Intent(this, NewChatActivity::class.java);
                    intent.putExtra("roomId",response.toInt());
                    startActivity(intent);
                    finish();
                },
                {
                        error ->
                    Log.d("error", error.toString());
                }
            ){}
            reqQueue.add(request);
        }


        //MainAcitivty로 전환되자마자 CalenderFragement 화면 보여주기
        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            calendar
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
                   var intent = Intent(this@MainActivity, AccountActivity::class.java)
                   startActivity(intent)
               }

           } //when문 끝
            true
        } //bnv 클릭 끝

    } // onCreate 끝

}