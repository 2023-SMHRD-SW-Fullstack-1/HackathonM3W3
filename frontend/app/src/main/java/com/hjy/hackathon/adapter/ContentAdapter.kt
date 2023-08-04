package com.hjy.hackathon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hjy.hackathon.ContentViewHolder
import com.hjy.hackathon.vo.ContentVO

class ContentAdapter(var context: Context, var template : Int, var data :ArrayList<ContentVO>)
    : RecyclerView.Adapter<ContentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        var template_View : View = LayoutInflater.from(context).inflate(template, parent, false)
        var VH : ContentViewHolder = ContentViewHolder(template_View)
        return VH
    }

    override fun getItemCount(): Int {
        return data.size

    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
//        var tv_title : TextView = holder.tv_title
//        var tv_price : TextView = holder.tv_price
//        var img : ImageView = holder.img
//
//        var Message : ContentVO = data.get(position)

        // 숫자에 콤마 추가 함수
        fun Int.formatWithCommas(): String {
            return String.format("%,d", this)
        }

        val content = data.get(position)
        holder.tv_title.text = content.tv_title

        // 숫자에 콤마 추가해서 TextView에 설정
        val priceWithCommas = content.tv_price.toIntOrNull()?.formatWithCommas()
        holder.tv_price.text = priceWithCommas ?: ""
        // holder.tv_price.text = data.get(position).tv_price

        holder.tv_title.text = data.get(position).tv_title
        holder.img.setImageResource(data.get(position).img!!);


        holder.tv_title.setOnClickListener{

        }



    }
}