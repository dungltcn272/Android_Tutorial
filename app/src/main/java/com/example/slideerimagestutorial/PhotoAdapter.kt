package com.example.slideerimagestutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide

class PhotoAdapter(private var mContext: Context, private var mListPhoto : List<Photo>) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view =LayoutInflater.from(container.context).inflate(R.layout.item_photo, container, false)
        val imgPhoto =view.findViewById<ImageView>(R.id.img_photo)
        val photo =mListPhoto[position]
        if(photo!=null){
            Glide.with(mContext).load(photo.resourceId).into(imgPhoto)
        }
        //add view to view group
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        if(mListPhoto!=null){
            return mListPhoto.size
        }
        return 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as View
    }
}