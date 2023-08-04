package com.example.checkappfirstinstallandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    companion object{
        const val KEY_FIRST_INSTALL="KEY_FIRST_INSTALL"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val mySharedPreferences =MySharedPreferences(this)
        Handler().postDelayed(object : Runnable{
            override fun run() {
                if(mySharedPreferences.getBooleanValue(KEY_FIRST_INSTALL)){
                    startActivity(MainActivity::class.java)
                }else{
                    startActivity(OnboardingActivity::class.java)
                    mySharedPreferences.putBooleanValue(KEY_FIRST_INSTALL, true)
                }
            }

        }, 2000)
    }
    fun startActivity( cls : Class<*>){
        val intent = Intent(this, cls)
        startActivity(intent)
        finish()
    }
}