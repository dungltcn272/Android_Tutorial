package com.example.roomdatabaseandroidtutorial

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseandroidtutorial.UserAdapter.IClickItemUser as IClickItemUser1

class UserAdapter (private val iClickItemUser: IClickItemUser) : RecyclerView.Adapter<UserAdapter.UserViewHolder> (){
    private lateinit var mListUser : List<User>
    interface IClickItemUser {
        fun updateUser(user: User)
        fun deleteUser(user: User)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list : List<User>){
        this.mListUser=list
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername =itemView.findViewById<TextView>(R.id.tv_username)
        val tvAddress =itemView.findViewById<TextView>(R.id.tv_address)
        val btnUpdate =itemView.findViewById<Button>(R.id.btn_update)
        val btnDelete =itemView.findViewById<Button>(R.id.btn_delete)
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
        holder.tvUsername.text=user.username
        holder.tvAddress.text=user.address

        holder.btnUpdate.setOnClickListener {
            iClickItemUser.updateUser(user)
        }
        holder.btnDelete.setOnClickListener {
            iClickItemUser.deleteUser(user)
        }
    }
}