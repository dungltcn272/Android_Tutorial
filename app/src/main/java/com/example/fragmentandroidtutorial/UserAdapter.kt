package com.example.fragmentandroidtutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val mListUser: List<User>, listener: IClickItemListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var mIClickItemListener: IClickItemListener =listener


    interface IClickItemListener{
        fun onClickItemUser(user: User)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(mListUser!=null){
            return mListUser.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user =mListUser[position]
        if(user==null){
            return
        }
        holder.tvName.text = user.name
        holder.tvName.setOnClickListener{
            mIClickItemListener.onClickItemUser(user)
        }
    }
}