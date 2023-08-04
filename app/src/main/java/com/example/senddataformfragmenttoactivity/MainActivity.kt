package com.example.senddataformfragmenttoactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() , ISendDataListener{
    private lateinit var edtEmail: EditText
    private lateinit var edtName: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtEmail=findViewById(R.id.edt_email)
        btnSend=findViewById(R.id.btn_send)
        edtName=findViewById(R.id.edt_name)

        btnSend.setOnClickListener {
            sendDataToFragment()
        }

    }

    private fun sendDataToFragment() {
        val strEmail =edtEmail.text.toString().trim()
        val strName=edtName.text.toString().trim()

        val user =User(strEmail, strName)


        val fragmentTransaction :FragmentTransaction =supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, HomeFragment.getInstance(user, this))
        fragmentTransaction.commit()
    }

    override fun sendData(user: User) {
        edtEmail.setText(user.email)
        edtName.setText(user.name)
    }
}