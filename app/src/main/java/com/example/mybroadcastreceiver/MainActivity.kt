package com.example.mybroadcastreceiver

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    companion object{
        const val MY_ACTION="com.dung.MY_ACTION"
        const val MY_TEXT="com.dung.MY_TEXT"
    }
    private lateinit var btnSendBroadcast : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSendBroadcast=findViewById(R.id.btn_send_broadcast)
        btnSendBroadcast.setOnClickListener {
            clickSendBroadcast()
        }

    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun clickSendBroadcast() {
        val intent =Intent(MY_ACTION)
//        val componentName =ComponentName("com.example.explicitbroadcast2", "com.example.explicitbroadcast2.MyBroadcast2")
//        intent.component = componentName
//        sendBroadcast(intent)
        intent.putExtra(MY_TEXT, "This is dung")
        val packageManager =packageManager
        val resolveInfoList =packageManager.queryBroadcastReceivers(intent, 0)
        for (info in resolveInfoList){
            val componentName =ComponentName(info.activityInfo.packageName, info.activityInfo.name)
            intent.component =componentName
            sendBroadcast(intent)
        }
    }
}