package com.hjy.hackathon.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hjy.hackathon.R
import com.hjy.hackathon.databinding.ActivityMainBinding
import com.hjy.hackathon.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    //바인딩
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        //파이차트 라이브러리 이용
        binding.pie










        return view
    }
}