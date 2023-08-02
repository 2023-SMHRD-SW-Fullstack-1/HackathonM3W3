package com.hjy.hackathon

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.hjy.hackathon.databinding.ActivityBoardBinding


class BoardActivity : ProfileActivity() {

//    // 바인딩
//    var _binding: ActivityBoardBinding? = null;
//    val binding get() = _binding!!;
//
//    lateinit var reqQueue: RequestQueue
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _binding = ActivityBoardBinding.inflate(layoutInflater);
//        setContentView(binding.root);
//
//
//        reqQueue = Volley.newRequestQueue(this@BoardActivity)
//        var btns = arrayOfNulls<ImageView>(15)
//        var categorys = arrayOfNulls<Int>(15)
//        var category = 0
//
//
//        // img 1~ 16까지 카테고리 번호 부여
//        for (index in 0 until 15) {
//
//            var id = resources.getIdentifier("img" + (index + 1), "id", packageName)
//            btns[index] = findViewById(id)
//            categorys[index] = index + 1
//
//            btns[index]!!.setOnClickListener {
//                category = categorys[index]!!
//            }
//        } // for문 끝
//
//
//        // 갤러리 사용
//        binding.boardGallery.setOnClickListener {
//            val intent = Intent(Intent.ACTION_GET_CONTENT);
//            intent.type = "image/*";
//            // 다른 액티비티가 시작된 후 그 액티비티의 결과를 다시 가져오고 싶을 때 사용!
//            startActivityForResult(intent, STORAGE_PERMISSION_CODE);
//
//        };
//
//        // 카메라 사용
//        binding.boardCamera.setOnClickListener {
//            checkPermission(android.Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
//            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(intent, CAMERA_PERMISSION_CODE);
//
//        }
//
//
//        //게시물등록버튼
//        binding.button.setOnClickListener{
//
//            //카테고리 선택 안했을 시
//            if(category==0){
//
//            }else{
//                // 서버로 넘기기
//
//
//            }
//
//
//        }
//
//
//
//
//
//    } //onCreate 끝
//
//
//
//    // 사진 불러오기
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        setImageView(requestCode, data, binding.boardImg);
//    }


}

