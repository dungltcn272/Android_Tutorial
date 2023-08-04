package com.example.senddataactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var btnSend: Button
    private lateinit var edtUsername: EditText

    companion object{
        const val MY_REQUEST_CODE =10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Activity 1"

        edtEmail=findViewById(R.id.edt_email)
        btnSend=findViewById(R.id.btn_send)
        edtUsername=findViewById(R.id.edt_username)

        btnSend.setOnClickListener {
            nextActivity()
        }
    }

    private fun nextActivity() {
        val strEmail =edtEmail.text.toString().trim()
        val strUsername=edtUsername.text.toString().trim()

        val user =User(strEmail, strUsername)

        val intent = Intent(this@MainActivity, Activity2::class.java)

        val bundle =Bundle()
        bundle.putSerializable("object_user", user)

        intent.putExtras(bundle)
        startActivityForResult(intent, MY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(MY_REQUEST_CODE==requestCode && resultCode==Activity.RESULT_OK){
            val user =data?.extras?.get("object_user") as User
            edtEmail.setText(user.email)
            edtUsername.setText(user.username)
        }
    }
}