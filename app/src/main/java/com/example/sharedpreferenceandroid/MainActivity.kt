package com.example.sharedpreferenceandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.sharedpreferenceandroid.data_local.DataLocalManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvUserInfo = findViewById<TextView>(R.id.tv_user_info)
        val userList =DataLocalManager.getListUser()
        tvUserInfo.text=userList[0].toString()+"\n"+userList[1].toString()
    }
}