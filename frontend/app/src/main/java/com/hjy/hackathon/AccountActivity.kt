package com.hjy.hackathon

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.google.gson.Gson
import com.hjy.hackathon.account.PasswordFragment
import com.hjy.hackathon.databinding.ActivityAccountBinding
import com.hjy.hackathon.vo.MemberVO

class AccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAccountBinding.inflate(layoutInflater);
        setContentView(binding.root);

        supportFragmentManager.beginTransaction().replace(R.id.flAccount, PasswordFragment()).commit();



    }
}