package com.hjy.hackathon

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.hjy.hackathon.databinding.ActivityCommentBinding
import com.hjy.hackathon.vo.FeedVO
import com.hjy.hackathon.vo.MemberVO
import com.hjy.hackathon.vo.SerializableFeed

class CommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCommentBinding.inflate(layoutInflater);
        setContentView(binding.root);
        val feed = intent.getSerializableExtra("feed") as SerializableFeed;
        val tvNick = findViewById<TextView>(R.id.tv_id);
        val ivProfile = findViewById<ImageView>(R.id.img_profile);
        val ivBoard = findViewById<ImageView>(R.id.img_content);
        val tvCategory = findViewById<TextView>(R.id.tv_category);
        val tvContent = findViewById<TextView>(R.id.tv_content);
        val tvCost = findViewById<TextView>(R.id.tv_cost);

        tvNick.text = feed.nick;
        tvCategory.text = feed.category;
        tvContent.text = feed.content;
        tvCost.text = feed.cost;

        if (feed.profile != null) {
            val imageBytesProfile = Base64.decode(feed.profile, 0);
            val imageProfile = BitmapFactory.decodeByteArray(imageBytesProfile, 0, imageBytesProfile.size);
            ivProfile.setImageBitmap(imageProfile);
        }

        if (feed.img != null) {
            val imageBytesContent = Base64.decode(feed.img, 0);
            val imageContent = BitmapFactory.decodeByteArray(imageBytesContent, 0, imageBytesContent.size);
            ivBoard.setImageBitmap(imageContent);
        }

        ivProfile.setOnClickListener {
            val spf = getSharedPreferences("mySPF", AppCompatActivity.MODE_PRIVATE);
            val member = spf.getString("member", "")!!;
            var memberVO = Gson().fromJson(member, MemberVO::class.java);
            if (memberVO.mb_id != feed.id) {
                var intent = Intent(this, MainActivity::class.java);
                intent.putExtra("other", feed.id);
                startActivity(intent);
                finish();
            }
        }

    }


}