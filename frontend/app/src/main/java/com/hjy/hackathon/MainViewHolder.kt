package com.hjy.hackathon

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MainViewHolder (var itemView : View) : ViewHolder(itemView){

    var Img_cg : ImageView
    var tv_cgType : TextView
    var tv_cgCost : TextView

    init {
        Img_cg = itemView.findViewById(R.id.img_profile)
        tv_cgType = itemView.findViewById(R.id.tv_cgType)
        tv_cgCost = itemView.findViewById(R.id.tv_cgCost)
    }
}