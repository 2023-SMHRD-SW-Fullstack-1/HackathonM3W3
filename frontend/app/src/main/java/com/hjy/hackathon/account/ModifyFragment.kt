package com.hjy.hackathon.account

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.hjy.hackathon.R
import com.hjy.hackathon.databinding.FragmentMainBinding
import com.hjy.hackathon.databinding.FragmentModifyBinding
import com.hjy.hackathon.vo.MemberVO

class ModifyFragment : Fragment() {

    private var _binding : FragmentModifyBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentModifyBinding.inflate(inflater, container, false);
        var view = binding.root;

        val spf = requireActivity().getSharedPreferences("mySPF", AppCompatActivity.MODE_PRIVATE);
        val member = spf.getString("member", "");
        var memberVO = Gson().fromJson(member, MemberVO::class.java);
        val imageBytes = Base64.decode(memberVO.mb_profile, 0)
        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        binding.ivMypage.setImageBitmap(image);

        return view;
    }

}