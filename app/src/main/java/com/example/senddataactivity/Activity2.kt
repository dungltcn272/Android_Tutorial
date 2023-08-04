package com.example.senddataactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Activity2 : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var btnUpdate: Button
    private lateinit var edtUsername: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        supportActionBar?.title = "Activity 2"

        edtEmail=findViewById(R.id.edt_email)
        edtUsername=findViewById(R.id.edt_username)
        btnUpdate=findViewById(R.id.btn_update)

        if(intent.extras!=null){
            val user=intent.extras?.get("object_user") as User

            edtEmail.setText(user.email)
            edtUsername.setText(user.username)
        }

        btnUpdate.setOnClickListener {
            backActivity()
        }
    }

    private fun backActivity() {
        val strEmailUpdate =edtEmail.text.toString().trim()
        val strUsernameUpdate =edtUsername.text.toString().trim()
        val returnIntent =Intent()

        val user =User(strEmailUpdate, strUsernameUpdate)
        val bundle =Bundle()
        bundle.putSerializable("object_user", user)

        returnIntent.putExtras(bundle)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}