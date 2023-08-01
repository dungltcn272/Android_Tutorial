package com.example.explicitbroadcast3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcast3: BroadcastReceiver() {
    companion object{
        const val MY_TEXT="com.dung.MY_TEXT"
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        val myText ="My broadcast 3 : " + intent?.getStringExtra(MY_TEXT)
        Toast.makeText(context, myText, Toast.LENGTH_LONG).show()
    }
}