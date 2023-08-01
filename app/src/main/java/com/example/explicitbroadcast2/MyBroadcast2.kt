package com.example.explicitbroadcast2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcast2 : BroadcastReceiver(){
    companion object{
        const val MY_TEXT="com.dung.MY_TEXT"
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        val myText ="My broadcast 2 :" + intent?.getStringExtra(MY_TEXT)
        Toast.makeText(context, myText, Toast.LENGTH_SHORT).show()
    }
}