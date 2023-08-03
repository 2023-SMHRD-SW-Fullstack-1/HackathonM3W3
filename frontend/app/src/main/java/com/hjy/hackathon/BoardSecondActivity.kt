package com.hjy.hackathon

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.databinding.ActivityBoardBinding
import com.hjy.hackathon.databinding.ActivityBoardSecondBinding
import com.hjy.hackathon.vo.BoardVO
import com.hjy.hackathon.vo.MemberVO

class BoardSecondActivity : ProfileActivity() {

    // 바인딩
    var _binding: ActivityBoardSecondBinding? = null;
    val binding get() = _binding!!;

    lateinit var reqQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityBoardSecondBinding.inflate(layoutInflater);
        setContentView(binding.root);

        reqQueue = Volley.newRequestQueue(this@BoardSecondActivity)

        // spf공간에서 id 가져오기
        val spf = getSharedPreferences("mySPF", Context.MODE_PRIVATE)
        val strMember = spf.getString("member", "기본값")
        val memberVO = Gson().fromJson(strMember, MemberVO::class.java)
        val id = memberVO.mb_id

        // 갤러리 사용
        binding.boardBtnGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT);
            intent.type = "image/*";
            // 다른 액티비티가 시작된 후 그 액티비티의 결과를 다시 가져오고 싶을 때 사용!
            startActivityForResult(intent, STORAGE_PERMISSION_CODE);

        };

        // 카메라 사용
        binding.boardBtnCamera.setOnClickListener {
            checkPermission(android.Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_PERMISSION_CODE);

        }

        // BoardAcitivity에서 intent통해서 가져온 값
        var secondIntent = intent
        var date = secondIntent.getStringExtra("date")
        var cost = secondIntent.getIntExtra("cost", 0)
        var category = secondIntent.getStringExtra("category")

        // 게시물등록 버튼 (서버로 보내기)
        binding.boardBtnSend.setOnClickListener {

            var img = encodeImgString
            var content = binding.boardContent.text.toString()


            val request = object : StringRequest(
                Request.Method.POST,
                "http://172.30.1.28:8888/board/write",
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
                    // 아이디, 날짜, 금액, 카테고리, 이미지, 내용
                    val board = BoardVO(id, date, cost, category, img, content)
                    params.put("board", Gson().toJson(board))
                    return params

                } //getParams 끝

            }

            reqQueue.add(request)

        } // 게시물등록 버튼 끝


} //onCreate 끝


    // 사진 불러오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setImageView(requestCode, data, binding.boardImg);
    }

}


