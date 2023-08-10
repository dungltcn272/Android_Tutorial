package com.example.clickitemrecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvNameUser=findViewById<TextView>(R.id.tv_name_user)

        val bundle= intent.extras ?: return
        val user=bundle.get("object user") as User
        tvNameUser.text=user.name


    }
}