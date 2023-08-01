package com.example.custombroadcast1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var btnSendBroadcast : Button
    private lateinit var tvReceived : TextView
    private val mBroadcastReceiver =object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if(MY_ACTION == intent?.action){
                val text =intent.getStringExtra(MY_TEXT)
                tvReceived.text=text
            }
        }

    }

    companion object{
        const val MY_ACTION="com.dung.ACTION"
        const val MY_TEXT="com.dung.TEXT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSendBroadcast =findViewById(R.id.btn_send_broadcast)
        tvReceived =findViewById(R.id.tv_received)

        btnSendBroadcast.setOnClickListener {
            clickSendBroadcast()
        }


    }

    private fun clickSendBroadcast() {
        val intent = Intent(MY_ACTION)
        intent.putExtra(MY_TEXT, "This is ...")
        sendBroadcast(intent)
    }

    override fun onStart() {
        super.onStart()
        val intentFilter =IntentFilter(MY_ACTION)
        registerReceiver(mBroadcastReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(mBroadcastReceiver)
    }
}