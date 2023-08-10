package com.example.filterlistwithsearchview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(var mListUser : List<User>, val mListUserOld: List<User>)  : RecyclerView.Adapter<UserAdapter.UserViewHolder>(), Filterable  {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgUser=itemView.findViewById<CircleImageView>(R.id.image_user)
        val tvName =itemView.findViewById<TextView>(R.id.tv_name)
        val tvAddress=itemView.findViewById<TextView>(R.id.tv_address)
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
        val user  =mListUser[position]
        if(user==null){
            return
        }
        holder.imgUser.setImageResource(user.image)
        holder.tvName.text = user.name
        holder.tvAddress.text=user.address
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val strSearch =constraint.toString()
                if(strSearch.isEmpty()){
                    mListUser=mListUserOld
                }else{
                    val list =ArrayList<User>()
                    for(user in mListUserOld){
                        if(user.name.toLowerCase().contains(strSearch)){
                            list.add(user)
                        }
                    }
                    mListUser=list
                }
                val filterResults =FilterResults()
                filterResults.values=mListUser
                return filterResults

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                mListUser = results?.values as List<User>
                notifyDataSetChanged()
            }

        }
    }

}