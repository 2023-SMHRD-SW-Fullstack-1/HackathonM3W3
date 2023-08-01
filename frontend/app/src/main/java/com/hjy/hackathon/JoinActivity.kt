package com.hjy.hackathon

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.databinding.ActivityJoinBinding

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
            val id = binding.etIdJoin.text;
            val pw = binding.etPwJoin.text;
            val pw2 = binding.etPw2Join.text;
            val img = encodeImgString;

            val request = object : StringRequest(
                Request.Method.POST,
                "http://172.30.1.23:8888/member/join",
                {
                    response ->

                },
                {
                    error ->
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val params : MutableMap<String, String> = HashMap<String, String>();

//                    val board = BoardVO(null, inputTitle, inputContent, writer, encodeImgString, null);
//                    params.put("board", Gson().toJson(board));
                    return params;
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setImageView(requestCode, data, binding.ivJoin);
    }







}