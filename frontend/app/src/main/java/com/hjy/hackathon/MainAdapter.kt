package com.hjy.hackathon


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hjy.hackathon.VO.MainVO
import androidx.recyclerview.widget.RecyclerView.Adapter

class MainAdapter (var context : Context, var template : Int, var data : ArrayList<MainVO> ) : Adapter<MainViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var template_View : View = LayoutInflater.from(context).inflate(template, parent, false)
        var VH =  MainViewHolder(template_View)
        return VH
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        var MainList : MainVO = data.get(position)
        holder.Img_cg.setImageResource(MainList.cg_img!!)

        holder.tv_cgType.text = data.get(position).cg_type
        holder.tv_cgCost.text = data.get(position).cg_cost.toString()
    }


}