package com.hjy.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.hjy.hackathon.databinding.ActivityLoginBinding
import com.hjy.hackathon.loginfragment.IntroFragment
import com.hjy.hackathon.loginfragment.LoginFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater);
        setContentView(binding.root);

        supportFragmentManager.beginTransaction().replace(
            binding.flLogin.id,
            IntroFragment()
        ).commit();

        Handler().postDelayed({
            supportFragmentManager.beginTransaction().replace(
                binding.flLogin.id,
                LoginFragment()
            ).commit();
        },3000)

    }
}