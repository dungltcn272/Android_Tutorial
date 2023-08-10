package com.example.startactivityforresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class DetailActivity : AppCompatActivity() {
    private lateinit var edtDataReceive: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "Main Screen"
        edtDataReceive=findViewById(R.id.edt_data_receive)
        val btnGoToDetail=findViewById<Button>(R.id.btn_go_to_main)
        btnGoToDetail.setOnClickListener {
            val strDataReceive=edtDataReceive.text.toString().trim()
            onClickBackToMain(strDataReceive)
        }
        val strDataReceive=intent.getStringExtra("data_intent")
        edtDataReceive.setText(strDataReceive)
    }

    private fun onClickBackToMain(strDataReceive: String) {
        val intent= Intent(this, MainActivity::class.java)
        intent.putExtra("data_result", strDataReceive)
        setResult(RESULT_OK, intent)
        finish()
    }
}