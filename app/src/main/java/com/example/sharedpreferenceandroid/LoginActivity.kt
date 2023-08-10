package com.example.sharedpreferenceandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.sharedpreferenceandroid.data_local.DataLocalManager
import com.example.sharedpreferenceandroid.model.User

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val user1= User(1, "dung", "thanh xuan ha noi")
        val user2= User(2, "dung2", "thanh xuan an do")
        val userList=ArrayList<User>()
        userList.add(user1)
        userList.add(user2)

        DataLocalManager.setListUser(userList)

        val btnGoToMain=findViewById<Button>(R.id.btn_go_to_main)
        btnGoToMain.setOnClickListener {
            val intent =Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}