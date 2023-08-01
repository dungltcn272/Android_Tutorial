package com.example.mvctutorial

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var tvMessage : TextView
    private lateinit var btnLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        tvMessage=findViewById(R.id.tv_message)
        btnLogin=findViewById(R.id.btn_login)

        btnLogin.setOnClickListener {
            clickLogin()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun clickLogin() {
        val stringEmail =edtEmail.text.toString().trim()
        val stringPassword=edtPassword.text.toString().trim()

        val user =User(stringEmail, stringPassword)
        tvMessage.visibility= View.VISIBLE
        if(user.isValidEmail() && user.isValidPassword()){
            tvMessage.text="LOGIN SUCCESS"
            tvMessage.setTextColor(resources.getColor(R.color.green))
        }else{
            tvMessage.text="EMAIL OR PASSWORD INVALID"
            tvMessage.setTextColor(resources.getColor(R.color.red))
        }

    }
}