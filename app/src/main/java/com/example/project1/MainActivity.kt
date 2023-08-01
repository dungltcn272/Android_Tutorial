package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {


    companion object{
        const val MY_ACTION = "com.dung.ACTION"
        const val MY_USER = "com.dung.USER"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSendBroadcast=findViewById<Button>(R.id.btn_send_broadcast)
        btnSendBroadcast.setOnClickListener {
            clickSendBroadcast()
        }
    }

    private fun clickSendBroadcast() {
        val intent =Intent(MY_ACTION)
        val user1 = User(1, "tin coder 1")
        val user2 = User(2, "tin coder 2")
        val list = ArrayList<User>()
        list.add(user1)
        list.add(user2)
        val gson = Gson()
        val jsonArray = gson.toJsonTree(list).asJsonArray
        val strJson =jsonArray.toString()

        intent.putExtra(MY_USER, strJson)
        sendBroadcast(intent)
        Toast.makeText(this, "SEND BROADCAST SUCCESS", Toast.LENGTH_SHORT).show()
    }
}