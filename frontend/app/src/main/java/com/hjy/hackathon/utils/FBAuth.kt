package com.hjy.hackathon.utils

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

//class FBAuth {
//
//    companion object {
//        lateinit var auth: FirebaseAuth
//
//        /**uid를 가져온다*/
//        fun getUid(): String {
//            auth = FirebaseAuth.getInstance() // class 안에서 인스턴스 생성할 때 getInstance()붙여준다
//            return auth.currentUser!!.uid
//        }
//
//        /**현재시간을 가져온다*/


//        /**사용자uid와 targetUid 비교
//         * @return Boolean*/
//        fun checkUid(targetUid: String): Boolean {
//            val userUid = FBAuth.getUid()
//            Log.d("check", " 사용자uid: $userUid, targetUid: $targetUid")
//            return targetUid == userUid
//        }
//    }
//}
