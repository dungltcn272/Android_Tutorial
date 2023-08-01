package com.example.swiperefreshlayouttutorial

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private lateinit var mListItem: List<ItemObject>

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ItemObject>){
        this.mListItem=list
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val tvName =itemView.findViewById<TextView>(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(mListItem!=null){
            return mListItem.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var itemObject : ItemObject =mListItem[position]
        if(itemObject==null){
            return
        }
        holder.tvName.text=itemObject.name
    }
}