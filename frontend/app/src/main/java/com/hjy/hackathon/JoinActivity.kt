package com.hjy.hackathon

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.databinding.ActivityJoinBinding
import com.hjy.hackathon.vo.MemberVO

class JoinActivity : ProfileActivity() {

    private var _binding : ActivityJoinBinding? = null;
    private val binding get() = _binding!!;

    lateinit var reqQueue : RequestQueue;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityJoinBinding.inflate(layoutInflater);
        setContentView(binding.root);

        reqQueue = Volley.newRequestQueue(this@JoinActivity);

        // 갤러리 사용
        binding.ibGalleryJoin.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT);
            intent.type = "image/*";
            // 다른 액티비티가 시작된 후 그 액티비티의 결과를 다시 가져오고 싶을 때 사용!
            startActivityForResult(intent, STORAGE_PERMISSION_CODE);

        };

        // 카메라 사용
        binding.ibCameraJoin.setOnClickListener  {
            checkPermission(android.Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_PERMISSION_CODE);

        }

        binding.btnJoin.setOnClickListener {
            val id = binding.etIdJoin.text.toString();
            val pw = binding.etPwJoin.text.toString();
            val pw2 = binding.etPw2Join.text.toString();
            val nick = binding.etNickJoin.text.toString();
            val img = encodeImgString;

            if (pw != pw2) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            } else {
                val request = object : StringRequest(
                    Request.Method.POST,
                    "http://172.30.1.28:8888/member/join",
                    {
                            response ->
                        Log.d("response", response.toString());
                        if (response.toString() == "Success") {
                            finish();
                        }
                    },
                    {
                            error ->
                        Log.d("error", error.toString());
                    }
                ){
                    override fun getParams(): MutableMap<String, String>? {
                        val params : MutableMap<String, String> = HashMap<String, String>();

                        val member = MemberVO(id, pw, nick, img);
                        params.put("member", Gson().toJson(member));
                        return params;
                    }
                }

                reqQueue.add(request);
            }


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setImageView(requestCode, data, binding.ivJoin);
    }



}