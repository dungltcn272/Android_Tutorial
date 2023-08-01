package com.example.getvideofromgallery

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class VideoAdapter(private val mActivity: Activity) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private var mListVideo : ArrayList<Video> =ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list : List<Video>){
        mListVideo.clear()
        mListVideo.addAll(list)
        notifyDataSetChanged()
    }
    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgVideo= itemView.findViewById<ImageView>(R.id.img_video)!!
        val layout_item =itemView.findViewById<RelativeLayout>(R.id.layout_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(mListVideo!=null){
            return mListVideo.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video=mListVideo[position]
        if(video==null){
            return
        }
        Glide.with(mActivity).load(video.thump).into(holder.imgVideo)
        holder.layout_item.setOnClickListener{
            val intent =Intent(mActivity, PlayVideoActivity::class.java)
            intent.putExtra("path_video", video.path)
            mActivity.startActivity(intent)

        }
    }

}