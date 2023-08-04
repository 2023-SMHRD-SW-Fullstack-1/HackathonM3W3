package com.hjy.hackathon.adapter

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.hjy.hackathon.vo.FeedVO
import com.hjy.hackathon.viewHolder.FeedViewHolder
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.hjy.hackathon.R
import com.hjy.hackathon.vo.BoardVO
import com.hjy.hackathon.vo.LikeVO
import org.json.JSONArray

class FeedAdapter(
    var activity: Activity,
    var template: Int,
    var data: ArrayList<FeedVO>
) :
    RecyclerView.Adapter<FeedViewHolder>() {
    val reqQueue: RequestQueue = Volley.newRequestQueue(activity);
    val reqQueue2: RequestQueue = Volley.newRequestQueue(activity);
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {

        var template_view: View =
            LayoutInflater.from(parent.context).inflate(template, parent, false)

        var VH: FeedViewHolder = FeedViewHolder(template_view)

        return VH

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feed = data[position]

        var likeCnt = "" // 좋아요 개수 저장할 변수 생성


        var id = feed.mb_id
        var idx = feed.board_idx

        if (feed.mb_profile != null) {
            val imageBytesProfile = Base64.decode(feed.mb_profile, 0);
            val imageProfile =
                BitmapFactory.decodeByteArray(imageBytesProfile, 0, imageBytesProfile.size);
            holder.img_profile.setImageBitmap(imageProfile);
        }

        if (feed.board_img != null) {
            val imageBytesContent = Base64.decode(feed.board_img, 0);
            val imageContent =
                BitmapFactory.decodeByteArray(imageBytesContent, 0, imageBytesContent.size);
            holder.img_content.setImageBitmap(imageContent);
        }

        holder.tv_nick.text = feed.mb_nick
        holder.tv_content.text = feed.board_content
        holder.tv_cost.text = feed.board_cost.toString()
        holder.tv_category.text = feed.board_cg



        var request4 = object : StringRequest(
            Request.Method.POST,
            "http://172.30.1.23:8888/board/likeCnt",
            { response ->
                Log.d("response", response.toString())

                var result = JSONArray(response)

                Log.d("리절트값", result.toString())
                val jsonObject = result.getJSONObject(0)
                likeCnt = jsonObject.getString("COUNT(board_idx)")
                Log.d("보내줄 값", likeCnt.toString())

                holder.tv_likeCnt.text = likeCnt.toString() // 좋아요 개수

            }, { error ->
                Log.d("error", error.toString())
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {

                val params: MutableMap<String, String> = HashMap<String, String>()
                val board = LikeVO(id, idx)
                params.put("board", Gson().toJson(board))
                return params
            }
        }
        reqQueue2.add(request4)


        var i = false;
        holder.img_like.setOnClickListener {
            if (i == true) { // 좋아요 취소했을 때
                holder.img_like.setImageResource(R.drawable.feedunlike)

                var request2 = object : StringRequest(
                    Request.Method.POST,
                    "http://172.30.1.23:8888/board/likeCnt",
                    { response ->
                        Log.d("response", response.toString())

                        var result = JSONArray(response)

                        Log.d("리절트값", result.toString())
                        val jsonObject = result.getJSONObject(0)
                        likeCnt = jsonObject.getString("COUNT(board_idx)")
                        Log.d("보내줄 값", likeCnt.toString())

                        holder.tv_likeCnt.text = likeCnt.toString() // 좋아요 개수

                    }, { error ->
                        Log.d("error", error.toString())
                    }
                ) {
                    override fun getParams(): MutableMap<String, String>? {

                        val params: MutableMap<String, String> = HashMap<String, String>()
                        val board = LikeVO(id, idx)
                        params.put("board", Gson().toJson(board))
                        return params
                    }
                }
                reqQueue2.add(request2)


                var request = object : StringRequest(
                    Request.Method.POST,
                    "http://172.30.1.23:8888/board/dislike",
                    { response ->
                        Log.d("response", response.toString())

                    }, { error ->
                        Log.d("error", error.toString())
                    }
                ) {
                    override fun getParams(): MutableMap<String, String>? {

                        val params: MutableMap<String, String> = HashMap<String, String>()
                        val board = LikeVO(id, idx)
                        params.put("board", Gson().toJson(board))
                        return params
                    }
                }
                reqQueue.add(request)


                var request3 = object : StringRequest(
                    Request.Method.POST,
                    "http://172.30.1.23:8888/board/likeCnt",
                    { response ->
                        Log.d("response", response.toString())

                        var result = JSONArray(response)

                        Log.d("리절트값", result.toString())
                        val jsonObject = result.getJSONObject(0)
                        likeCnt = jsonObject.getString("COUNT(board_idx)")
                        Log.d("보내줄 값", likeCnt.toString())

                        holder.tv_likeCnt.text = likeCnt.toString() // 좋아요 개수

                    }, { error ->
                        Log.d("error", error.toString())
                    }
                ) {
                    override fun getParams(): MutableMap<String, String>? {

                        val params: MutableMap<String, String> = HashMap<String, String>()
                        val board = LikeVO(id, idx)
                        params.put("board", Gson().toJson(board))
                        return params
                    }
                }
                reqQueue2.add(request3)



                i = false

            } else { // 좋아요 눌렀을 때
                holder.img_like.setImageResource(R.drawable.feedlike)

                var request2 = object : StringRequest(
                    Request.Method.POST,
                    "http://172.30.1.23:8888/board/likeCnt",
                    { response ->
                        Log.d("response", response.toString())

                        var result = JSONArray(response)

                        Log.d("리절트값", result.toString())

                        val jsonObject = result.getJSONObject(0)
                        likeCnt = jsonObject.getString("COUNT(board_idx)") // 좋아요 개수 불러옴

                        Log.d("보내줄 값", likeCnt.toString())

                        holder.tv_likeCnt.text = likeCnt.toString() // 좋아요 개수 저장

                    }, { error ->
                        Log.d("error", error.toString())
                    }
                ) {
                    override fun getParams(): MutableMap<String, String>? {

                        val params: MutableMap<String, String> = HashMap<String, String>()
                        val board = LikeVO(id, idx)
                        params.put("board", Gson().toJson(board))
                        return params
                    }
                }
                reqQueue2.add(request2)


                var request = object : StringRequest(
                    Request.Method.POST,
                    "http://172.30.1.23:8888/board/like",
                    { response ->
                        Log.d("response", response.toString())

                    }, { error ->
                        Log.d("error", error.toString())
                    }
                ) {
                    override fun getParams(): MutableMap<String, String>? {

                        val params: MutableMap<String, String> = HashMap<String, String>()
                        // 아이디, 게시글 식별자
                        val board = LikeVO(id, idx)
                        params.put("board", Gson().toJson(board))
                        return params
                    }
                }
                reqQueue.add(request)

                var request3 = object : StringRequest(
                    Request.Method.POST,
                    "http://172.30.1.23:8888/board/likeCnt",
                    { response ->
                        Log.d("response", response.toString())

                        var result = JSONArray(response)

                        Log.d("리절트값", result.toString())

                        val jsonObject = result.getJSONObject(0)
                        likeCnt = jsonObject.getString("COUNT(board_idx)") // 좋아요 개수 불러옴

                        Log.d("보내줄 값", likeCnt.toString())

                        holder.tv_likeCnt.text = likeCnt.toString() // 좋아요 개수 저장

                    }, { error ->
                        Log.d("error", error.toString())
                    }
                ) {
                    override fun getParams(): MutableMap<String, String>? {

                        val params: MutableMap<String, String> = HashMap<String, String>()
                        val board = LikeVO(id, idx)
                        params.put("board", Gson().toJson(board))
                        return params
                    }
                }
                reqQueue2.add(request3)

                i = true
            }
        }


    }

}