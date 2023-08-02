package com.hjy.hackathon.mainfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.R
import com.hjy.hackathon.vo.FeedVO
import com.hjy.hackathon.adapter.FeedAdapter
import org.json.JSONArray

class FeedFragment : Fragment() {

    lateinit var rv_feedList: RecyclerView

    lateinit var reqQueue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        rv_feedList = view.findViewById(R.id.rv_feedList)

        reqQueue = Volley.newRequestQueue(requireActivity())

        val data = ArrayList<FeedVO>()

        val request = object : StringRequest(
            Request.Method.GET,
            "http://172.30.1.23:8888/board",
            { response ->
                Log.d("response", response.toString())

                var result = JSONArray(response)

                for (i in 0 until result.length()) {
                    val board = Gson().fromJson(result.get(i).toString(), FeedVO::class.java)
                    data.add(board)
                }

                val adapter : FeedAdapter = FeedAdapter(requireContext(),R.layout.feed_item ,data)
                rv_feedList.layoutManager = LinearLayoutManager(requireActivity())
                rv_feedList.adapter = adapter

            },
            { error ->
                Log.d("error", error.toString())
            }
        ) {}

        reqQueue.add(request)

        return view
    }

}