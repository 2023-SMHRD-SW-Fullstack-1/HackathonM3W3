package com.hjy.hackathon

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.hjy.hackathon.databinding.ActivityJoinBinding
import java.io.ByteArrayOutputStream

class JoinActivity : AppCompatActivity() {

    val STORAGE_PERMISSION_CODE = 1000;
    val CAMERA_PERMISSION_CODE = 2000;

    private var _binding : ActivityJoinBinding? = null;
    private val binding get() = _binding!!;

    lateinit var encodeImgString : String;

    lateinit var reqQueue : RequestQueue;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityJoinBinding.inflate(layoutInflater);
        setContentView(binding.root);

        reqQueue = Volley.newRequestQueue(this@JoinActivity);

        binding.ibGalleryJoin.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT);
            intent.type = "image/*";
            // 다른 액티비티가 시작된 후 그 액티비티의 결과를 다시 가져오고 싶을 때 사용!
            startActivityForResult(intent, STORAGE_PERMISSION_CODE);
        };

        binding.ibCameraJoin.setOnClickListener {

            checkPermission(android.Manifest.permission.CAMERA,
                CAMERA_PERMISSION_CODE)

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_PERMISSION_CODE);
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            STORAGE_PERMISSION_CODE -> {
                // image uri 가져오기
                val selectedImgUri = data?.data;

                if (selectedImgUri != null) {
                    // uri -> bitmap 으로 변환
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImgUri);
                    binding.ivJoin.setImageBitmap(bitmap);

                    val options = BitmapFactory.Options();
                    options.inSampleSize = 4; // 4개의 픽셀 -> 1개의 픽셀 => 1/4 크기로 변환

                    // filter : true(선명)
                    val resized = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                    encodeBitmapImg(resized);

                }
            }
            CAMERA_PERMISSION_CODE -> {
                val extras = data?.extras;
                if (extras != null) {
                    val bitmap = extras.get("data") as Bitmap;
                    binding.ivJoin.setImageBitmap(bitmap);

                    val options = BitmapFactory.Options();
                    options.inSampleSize = 4; // 4개의 픽셀 -> 1개의 픽셀 => 1/4 크기로 변환

                    // filter : true(선명)
                    val resized = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                    encodeBitmapImg(resized);
                }

            }

        }
    }


    // bitmap -> String (Base64)
    private  fun encodeBitmapImg(bitmap : Bitmap) {
        // ByteArray 생성할 수 있는 stream
        val byteArrayOutputStream = ByteArrayOutputStream();
        // bitmap 압축
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        // img => array
        val byteOfImg = byteArrayOutputStream.toByteArray();

        // encoding : ByteArray ->  String(Base64 - encoding)
        encodeImgString = Base64.encodeToString(byteOfImg, Base64.DEFAULT);

    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@JoinActivity, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this@JoinActivity, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this@JoinActivity, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@JoinActivity, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@JoinActivity, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@JoinActivity, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@JoinActivity, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}