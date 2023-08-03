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
        var tv_title : TextView = holder.tv_title
        var tv_price : TextView = holder.tv_price
        var img : ImageView = holder.img

        var Message : ContentVO = data.get(position)

        holder.tv_title.text = data.get(position).tv_title
        holder.tv_price.text = data.get(position).tv_price



    }
}