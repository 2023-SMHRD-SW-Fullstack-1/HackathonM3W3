package com.hjy.hackathon.loginfragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.JoinActivity
import com.hjy.hackathon.MainActivity
import com.hjy.hackathon.R
import com.hjy.hackathon.databinding.FragmentLoginBinding
import com.hjy.hackathon.vo.MemberVO


class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null;
    private val binding get() = _binding!!;

    lateinit var reqQueue : RequestQueue;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false);
        val view = binding.root

        reqQueue = Volley.newRequestQueue(requireActivity());

        binding.btnLogin.setOnClickListener {
            Log.d("asd", "asd");
            val id = binding.etLoginId.text.toString();
            val pw = binding.etLoginPw.text.toString();

            val request = object : StringRequest(
                Request.Method.POST,
                "http://172.30.1.28:8888/member/login",
                {
                        response ->
                    Log.d("response", response.toString());
                    if (response.toString() != "Fail") {
                        val spf = requireActivity().getSharedPreferences("mySPF", Context.MODE_PRIVATE);
                        val editor = spf.edit();
                        editor.putString("member", response.toString());

                        editor.commit();
                        var intent = Intent(requireActivity(), MainActivity::class.java);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                },
                {
                        error ->
                    Log.d("error", error.toString());
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val params : MutableMap<String, String> = HashMap<String, String>();

                    val member = MemberVO(id, pw, null, null);
                    params.put("member", Gson().toJson(member));
                    return params;
                }
            }

            reqQueue.add(request);

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