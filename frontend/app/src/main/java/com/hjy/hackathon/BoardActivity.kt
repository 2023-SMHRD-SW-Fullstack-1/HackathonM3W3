package com.hjy.hackathon

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.databinding.ActivityBoardBinding
import com.hjy.hackathon.vo.BoardVO
import java.io.ByteArrayOutputStream


class BoardActivity : ProfileActivity() {

    // 바인딩
    var _binding: ActivityBoardBinding? = null;
    val binding get() = _binding!!;

    lateinit var reqQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBoardBinding.inflate(layoutInflater);
        setContentView(binding.root);


        reqQueue = Volley.newRequestQueue(this@BoardActivity)
        var btns = arrayOfNulls<ImageView>(15)
        var categorys = arrayOfNulls<Int>(15)
        var category = 0


        // img 1~ 16까지 카테고리 번호 부여
        for (index in 0 until 15) {

            var id = resources.getIdentifier("img" + (index + 1), "id", packageName)
            btns[index] = findViewById(id)
            categorys[index] = index + 1

            btns[index]!!.setOnClickListener {
                btns[index]!!.alpha = 0.5f       //불투명도
                category = categorys[index]!!   //카테고리에 번호 부여
            }
        } // for문 끝


        // 갤러리 사용
        binding.boardGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT);
            intent.type = "image/*";
            // 다른 액티비티가 시작된 후 그 액티비티의 결과를 다시 가져오고 싶을 때 사용!
            startActivityForResult(intent, STORAGE_PERMISSION_CODE);

        };

        // 카메라 사용
        binding.boardCamera.setOnClickListener {
            checkPermission(android.Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_PERMISSION_CODE);

        }


        //게시물등록버튼
        binding.button.setOnClickListener {

            if (category == 0) {// 카테고리 선택 안했을 시
                Toast.makeText(this@BoardActivity, "카테고리를 선택해주세요", Toast.LENGTH_SHORT).show()
            } else {  // 서버로 넘기기

                //입력한 값 가져오기
                var inputCost = binding.boardCost.toString().toInt()
                var inputCg = category
                var inputImg = encodeImgString
                var inputContent = binding.boardContent.toString()

                //요청
                val request = object : StringRequest(
                    Request.Method.POST,
                    "http://172.30.1.23:8888/board/write",
                    { response ->
                        Log.d("response", response.toString())
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }, { error ->
                        Log.d("error", error.toString())
                    }
                ) {
                    override fun getParams(): MutableMap<String, String>? {

                        val params: MutableMap<String, String> = HashMap<String, String>()
                        val board = BoardVO(inputCost, inputCg, inputImg, inputContent)
                        params.put("board", Gson().toJson(board))
                        return params

                    } //getParams 끝

                } // request 끝

            } // else문 끝

        }

    } //onCreate 끝


    // 사진 불러오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setImageView(requestCode, data, binding.boardImg);

    }

}

