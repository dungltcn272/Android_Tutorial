package com.example.tablayouttutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var mTabLayout : TabLayout
    private lateinit var mViewPage : ViewPager
    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTabLayout=findViewById(R.id.tab_layout)
        mViewPage=findViewById(R.id.view_page)

        mViewPagerAdapter=ViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        mViewPage.adapter=mViewPagerAdapter
        mTabLayout.setupWithViewPager(mViewPage)
    }
}