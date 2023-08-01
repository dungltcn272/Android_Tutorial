package com.example.project2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var tvReceived : TextView
    companion object{
        const val MY_ACTION = "com.dung.ACTION"
        const val MY_USER = "com.dung.USER"
    }
    private val mBroadcastReceiver =object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if(MY_ACTION ==intent?.action){
                val strJson =intent.getStringExtra(MY_USER)
                val list =getListUser(strJson)
                var listUsername=""
                for (i in 0 until list.size){
                    listUsername =listUsername + list[i].name +"\n"
                }

                tvReceived.text=listUsername
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvReceived=findViewById(R.id.tv_received)

    }

    override fun onStart() {
        super.onStart()
        val intentFilter =IntentFilter(MY_ACTION)
        registerReceiver(mBroadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mBroadcastReceiver)
        tvReceived=findViewById(R.id.tv_received)
    }
    private fun getListUser(strJson: String?) : List<User>{
        val list =ArrayList<User>()
        val jsonArray =JSONArray(strJson)
        var jsonObject: JSONObject
        var user: User
        val gson=Gson()
        for(i in 0 until jsonArray.length()){
            jsonObject= jsonArray.getJSONObject(i)
            user=gson.fromJson(jsonObject.toString(), User::class.java)
            list.add(user)
        }
        return list
    }
}