package com.example.bottomnavigationbartutorial.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPageAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> return HomeFragment()
            1 -> return FavoriteFragment()
            2 -> return HistoryFragment()
            else -> HomeFragment()
        }
    }
}