package com.example.tablayouttutorial

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
            0 -> HomeFragment()
            1 -> FavoriteFragment()
            2 -> MyPageFragment()
            else -> HomeFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        var title =""
        when(position){
            0-> title = "Home"
            1 -> title ="Favorite"
            2 -> title ="My Page"
        }
        return title
    }
}