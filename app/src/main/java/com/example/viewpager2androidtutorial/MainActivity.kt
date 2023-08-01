package com.example.viewpager2androidtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var radioGroup1 :  RadioGroup
    private lateinit var radioGroup2 :  RadioGroup
    private lateinit var radioGroup3 :  RadioGroup
    private lateinit var viewPager2Adapter : ViewPager2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2=findViewById(R.id.viewPager_2)
        radioGroup1=findViewById(R.id.radio_group_1)
        radioGroup2=findViewById(R.id.radio_group_2)
        radioGroup3=findViewById(R.id.radio_group_3)

        radioGroup1.check(R.id.rdb_ltr)
        radioGroup1.check(R.id.rdb_horizontal)
        radioGroup1.check(R.id.rdb_normal)

        viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2.adapter=viewPager2Adapter

        radioGroup1.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when(checkedId){
                    R.id.rdb_ltr -> viewPager2.layoutDirection= ViewPager2.LAYOUT_DIRECTION_LTR
                    R.id.rdb_rtl -> viewPager2.layoutDirection=ViewPager2.LAYOUT_DIRECTION_RTL
                }
            }
        })
        radioGroup2.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when(checkedId){
                    R.id.rdb_vertical -> viewPager2.orientation=ViewPager2.ORIENTATION_VERTICAL
                    R.id.rdb_horizontal -> viewPager2.orientation=ViewPager2.ORIENTATION_HORIZONTAL
                }
            }
        })
        radioGroup3.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when(checkedId){
                    R.id.rdb_normal -> viewPager2.setPageTransformer(null)
                    R.id.rdb_zoomOut -> viewPager2.setPageTransformer(ZoomOutPageTransformer())
                    R.id.rdb_depth -> viewPager2.setPageTransformer(DepthPageTransformer())
                }
            }
        })



    }
}