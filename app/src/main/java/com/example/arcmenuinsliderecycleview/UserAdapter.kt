package com.example.arcmenuinsliderecycleview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private lateinit var mListUser : List<String>

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<String>){
        this.mListUser =list
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvName: TextView =itemView.findViewById(R.id.tv_name)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userName =mListUser[position]
        holder.tvName.text=userName
    }

    override fun getItemCount(): Int {
        if (mListUser!=null){
            return mListUser.size
        }
        return 0
    }
}