package com.example.slideerimagestutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.viewpager.widget.ViewPager
import me.relex.circleindicator.CircleIndicator
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager :ViewPager
    private lateinit var circleIndicator : CircleIndicator
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var mListPhoto: List<Photo>
    private var mTimer : Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager=findViewById(R.id.view_pager)
        circleIndicator=findViewById(R.id.circle_indicator)

        mListPhoto=getListPhoto()
        photoAdapter= PhotoAdapter(this, mListPhoto)
        viewPager.adapter=photoAdapter

        circleIndicator.setViewPager(viewPager)
        photoAdapter.registerDataSetObserver(circleIndicator.dataSetObserver)

        autoSlideImages()
    }

    private fun getListPhoto(): List<Photo> {
        val list =ArrayList<Photo>()
        list.add(Photo(R.drawable.img1))
        list.add(Photo(R.drawable.img2))
        list.add(Photo(R.drawable.img3))
        list.add(Photo(R.drawable.img4))
        return list
    }

    private fun autoSlideImages(){
        if(mListPhoto == null || mListPhoto.isEmpty() || viewPager==null){
            return
        }
        // Init timer
        if(mTimer==null){
            mTimer= Timer()
        }
        val task =object :TimerTask(){
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    run {
                        var currentItem = viewPager.currentItem
                        val totalItem = mListPhoto.size - 1
                        if (currentItem < totalItem) {
                            currentItem++
                            viewPager.currentItem = currentItem
                        } else {
                            viewPager.currentItem = 0
                        }
                    }
                }
            }
        }
        mTimer?.schedule(task, 3000, 3000)


    }

    override fun onDestroy() {
        super.onDestroy()
        if(mTimer!=null){
            mTimer?.cancel()
            mTimer = null
        }
    }
}