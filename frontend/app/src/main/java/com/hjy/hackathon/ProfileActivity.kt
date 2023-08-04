package com.hjy.hackathon

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.util.Base64
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream

open class ProfileActivity : AppCompatActivity() {

    val STORAGE_PERMISSION_CODE = 1000;
    val CAMERA_PERMISSION_CODE = 2000;

    var encodeImgString : String? = null;

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }



    // 권한 요청
    protected fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    // bitmap -> String (Base64)
    protected fun encodeBitmapImg(bitmap : Bitmap) {
        // ByteArray 생성할 수 있는 stream
        val byteArrayOutputStream = ByteArrayOutputStream();
        // bitmap 압축
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        // img => array
        val byteOfImg = byteArrayOutputStream.toByteArray();

        // encoding : ByteArray ->  String(Base64 - encoding)
        encodeImgString = Base64.encodeToString(byteOfImg, Base64.DEFAULT);

    }

    protected fun setImageView(requestCode: Int, data : Intent?, view : ImageView) {
        var bitmap : Bitmap? = null;
        when (requestCode) {
            STORAGE_PERMISSION_CODE -> {
                // image uri 가져오기
                val selectedImgUri = data?.data;

                if (selectedImgUri != null) {
                    // uri -> bitmap 으로 변환
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImgUri);
                    view.setImageBitmap(bitmap);

                }
            }
            CAMERA_PERMISSION_CODE -> {
                val extras = data?.extras;
                if (extras != null) {
                    bitmap = extras.get("data") as Bitmap;
                    view.setImageBitmap(bitmap);

                }

            }

        }

        val options = BitmapFactory.Options();
        options.inSampleSize = 1; // 4개의 픽셀 -> 1개의 픽셀 => 1/4 크기로 변환

        // filter : true(선명)
        val resized = Bitmap.createScaledBitmap(bitmap!!, 200, 200, true);
        encodeBitmapImg(resized);
    }


}