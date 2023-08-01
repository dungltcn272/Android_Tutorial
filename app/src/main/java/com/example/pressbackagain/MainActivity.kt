package com.example.pressbackagain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var backPressTime : Long = 0
    private lateinit var mToast: Toast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if(backPressTime +2000 > System.currentTimeMillis()){
            mToast.cancel()
            super.onBackPressed()
        }else{
            mToast=Toast.makeText(this, "Press back again to exit", Toast.LENGTH_LONG)
            mToast.show()
        }
        backPressTime=System.currentTimeMillis()
    }
}