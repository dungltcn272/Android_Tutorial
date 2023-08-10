package com.example.clickitemrecycleview
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clickitemrecycleview.my_interface.IClickItemUserListener

class UserAdapter (private val mListUser: List<User>, private val iClickItemUserListener: IClickItemUserListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAvatar=itemView.findViewById<ImageView>(R.id.img_avatar)
        val tvName=itemView.findViewById<TextView>(R.id.tv_name)
        val tvAddress=itemView.findViewById<TextView>(R.id.tv_address)
        val layoutItem =itemView.findViewById<RelativeLayout>(R.id.layout_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(mListUser!=null){
            return mListUser.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user= mListUser[position]
        holder.imgAvatar.setImageResource(user.resourceId)
        holder.tvName.text=user.name
        holder.tvAddress.text=user.address
        holder.layoutItem.setOnClickListener{
            iClickItemUserListener.onClickItemUser(user)
        }
    }


}