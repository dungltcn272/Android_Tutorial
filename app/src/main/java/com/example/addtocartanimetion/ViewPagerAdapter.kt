package com.example.addtocartanimetion

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
         return when(position){
            0 -> ProductFragment()
            1-> CartFragment()
            2-> NoticeFragment()
            else -> ProductFragment()
        }
    }
}