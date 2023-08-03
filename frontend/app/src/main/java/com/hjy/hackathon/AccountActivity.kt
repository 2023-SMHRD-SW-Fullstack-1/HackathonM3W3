package com.hjy.hackathon

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.account.PasswordFragment
import com.hjy.hackathon.databinding.ActivityAccountBinding
import com.hjy.hackathon.databinding.FragmentModifyBinding
import com.hjy.hackathon.vo.MemberVO

class AccountActivity : ProfileActivity() {

    private var _binding : ActivityAccountBinding? = null;
    val binding get() = _binding!!;

    lateinit var reqQueue : RequestQueue;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAccountBinding.inflate(layoutInflater);
        setContentView(binding.root);

        reqQueue = Volley.newRequestQueue(this);

        val spf = getSharedPreferences("mySPF", AppCompatActivity.MODE_PRIVATE);
        val editor = spf.edit();
        val member = spf.getString("member", "");
        var memberVO = Gson().fromJson(member, MemberVO::class.java);
        val imageBytes = Base64.decode(memberVO.mb_profile, 0)
        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        binding.tvIdAccount.text = memberVO.mb_id;
        binding.etNickAccount.setText(memberVO.mb_nick);
        binding.etPwAccount.setText(memberVO.mb_pw);
        binding.etPw2Account.setText(memberVO.mb_pw);
        binding.ivMypage.setImageBitmap(image);

        // 갤러리 사용
        binding.ibGalleryAccount.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT);
            intent.type = "image/*";
            // 다른 액티비티가 시작된 후 그 액티비티의 결과를 다시 가져오고 싶을 때 사용!
            startActivityForResult(intent, STORAGE_PERMISSION_CODE);

        };

        // 카메라 사용
        binding.ibCameraAccount.setOnClickListener  {
            checkPermission(android.Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_PERMISSION_CODE);

        }

        binding.btnModify.setOnClickListener {
            val id = memberVO.mb_id
            val nick = binding.etNickAccount.text.toString();
            val pw = binding.etPwAccount.text.toString();
            val pw2 = binding.etPw2Account.text.toString();
            val img = if (encodeImgString == null) memberVO.mb_profile else encodeImgString;
            if (pw == pw2) {
                val request = object : StringRequest(
                    Request.Method.POST,
                    "http://172.30.1.28:8888/member/update",
                    {
                            response ->
                        Log.d("response", response.toString());
                        if (response.toString() == "Success") {
                            val m = Gson().toJson(memberVO);
                            editor.putString("member", m);
                            editor.commit();
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
            } else {
                Toast.makeText(this,"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setImageView(requestCode, data, binding.ivMypage);
    }
}