package com.example.rxretrofitmvvmpartern.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rxretrofitmvvmpartern.R
import com.example.rxretrofitmvvmpartern.model.ObjectData
import com.example.rxretrofitmvvmpartern.databinding.ItemDataBinding

class MyAdapter (private val listData: List<ObjectData>): RecyclerView.Adapter<MyAdapter.Companion.MyViewHolder>(){
    companion object{
        class MyViewHolder(val itemDataBinding: ItemDataBinding):
            RecyclerView.ViewHolder(itemDataBinding.root)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemDataBinding=DataBindingUtil.inflate<ItemDataBinding>(LayoutInflater.from(parent.context),
            R.layout.item_data, parent, false)
        return MyViewHolder(itemDataBinding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val objectData=listData[position]
        holder.itemDataBinding.objectDataViewModel=objectData
    }
}