package com.example.bottomnavigationbartutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.bottomnavigationbartutorial.fragment.ViewPageAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var navigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewPager = findViewById(R.id.view_pager)

        setUpViewPager()
        navigationView = findViewById(R.id.bottom_nav)
        navigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    mViewPager.currentItem = 0
                    true
                }
                R.id.action_favorite -> {
                    mViewPager.currentItem = 1
                    true
                }
                R.id.action_history -> {
                    mViewPager.currentItem = 2
                    true
                }
                else -> false

            }
        }
    }

    private fun setUpViewPager() {
        val viewPagerAdapter = ViewPageAdapter(
            supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        mViewPager.adapter = viewPagerAdapter
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> navigationView.menu.findItem(R.id.action_home).isChecked = true
                    1 -> navigationView.menu.findItem(R.id.action_favorite).isChecked = true
                    2 -> navigationView.menu.findItem(R.id.action_history).isChecked = true
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

    }
}