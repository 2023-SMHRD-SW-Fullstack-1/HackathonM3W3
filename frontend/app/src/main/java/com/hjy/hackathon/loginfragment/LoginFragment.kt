package com.hjy.hackathon.loginfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hjy.hackathon.JoinActivity
import com.hjy.hackathon.MainActivity
import com.hjy.hackathon.R
import com.hjy.hackathon.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false);
        val view = binding.root

        binding.btnLogin.setOnClickListener {
            var intent = Intent(requireActivity(), MainActivity::class.java);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        binding.btnJoinAct.setOnClickListener {
            var intent = Intent(requireActivity(), JoinActivity::class.java);
            startActivity(intent);
        }

        return view;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }

}