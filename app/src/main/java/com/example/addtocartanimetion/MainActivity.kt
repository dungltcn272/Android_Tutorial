@file:Suppress("DEPRECATION")

package com.example.addtocartanimetion

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager
import com.aurelhubert.ahbottomnavigation.notification.AHNotification


class MainActivity : AppCompatActivity() {
    private lateinit var ahBottomNavigation: AHBottomNavigation
    private lateinit var ahBottomNavigationViewPager: AHBottomNavigationViewPager
    private lateinit var adapter: ViewPagerAdapter

    private lateinit var viewEndAnimation: View
    private lateinit var viewAnimation : ImageView
    private var mCountProduct: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewEndAnimation=findViewById(R.id.view_end_animation)
        viewAnimation=findViewById(R.id.view_animation)

        ahBottomNavigation = findViewById(R.id.AHBottomNavigation)
        ahBottomNavigationViewPager = findViewById(R.id.AHBottomNavigationViewPager)

        adapter = ViewPagerAdapter(
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        ahBottomNavigationViewPager.adapter = adapter
        ahBottomNavigationViewPager.setPagingEnabled(true)

        // Create items
        val item1 =
            AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_product, R.color.color_tab_1)
        val item2 = AHBottomNavigationItem(
            R.string.tab_2,
            R.drawable.ic_cart,
            R.color.color_tab_2
        )
        val item3 = AHBottomNavigationItem(
            R.string.tab_3,
            R.drawable.ic_notice,
            R.color.color_tab_3
        )

        // Add items
        ahBottomNavigation.addItem(item1)
        ahBottomNavigation.addItem(item2)
        ahBottomNavigation.addItem(item3)

        ahBottomNavigation.setOnTabSelectedListener{ position, wasSelected ->
            ahBottomNavigationViewPager.currentItem=position
            true
        }
        ahBottomNavigationViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
            }

            override fun onPageSelected(position: Int) {
                ahBottomNavigation.currentItem = position
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }
    fun getViewAnimation(): ImageView {
        return viewAnimation
    }
    fun getViewEndAnimation(): View {
        return viewEndAnimation
    }
    fun setCountProductInCart(count: Int){
        mCountProduct=count
        val notification = AHNotification.Builder()
            .setText(count.toString())
            .setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.red
                )
            )
            .setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.white
                )
            )
            .build()
        ahBottomNavigation.setNotification(notification, 1)
    }
    fun getCountProduct(): Int{
        return mCountProduct
    }
}