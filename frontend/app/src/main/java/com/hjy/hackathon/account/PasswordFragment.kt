package com.hjy.hackathon.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.hjy.hackathon.R
import com.hjy.hackathon.databinding.FragmentPasswordBinding
import com.hjy.hackathon.vo.MemberVO

class PasswordFragment : Fragment() {

    private var _binding : FragmentPasswordBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false);
        var view = binding.root;

        val spf = requireActivity().getSharedPreferences("mySPF", AppCompatActivity.MODE_PRIVATE);
        val member = spf.getString("member", "");
        var memberVO = Gson().fromJson(member, MemberVO::class.java);

        binding.tvPwId.text = memberVO.mb_id;

        binding.btnPw.setOnClickListener {
            if (memberVO.mb_pw == binding.etPwPw.text.toString()) {
                activity?.supportFragmentManager!!.beginTransaction().replace(R.id.flAccount, ModifyFragment()).commit();
            } else {
                Toast.makeText(requireActivity(), "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
            }
        }

        return view;
    }

}