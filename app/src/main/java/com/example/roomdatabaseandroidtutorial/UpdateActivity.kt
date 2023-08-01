package com.example.roomdatabaseandroidtutorial

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.roomdatabaseandroidtutorial.database.UserDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var edtUsername : EditText
    private lateinit var edtAddress : EditText
    private lateinit var btnUpdateUser : Button
    private lateinit var mUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        edtUsername=findViewById(R.id.edit_username)
        edtAddress=findViewById(R.id.edit_address)
        btnUpdateUser= findViewById(R.id.btn_update_user)

        mUser=intent.extras?.get("object_user") as User
        if(mUser!=null){
            edtUsername.setText(mUser.username)
            edtAddress.setText(mUser.address)
        }

        btnUpdateUser.setOnClickListener {
            updateUser()
        }
    }

    private fun updateUser() {
        val strUsername =edtUsername.text.toString().trim()
        val strAddress =edtAddress.text.toString().trim()

        if(TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)){
            return
        }
        // update User
        mUser.username=strUsername
        mUser.address=strAddress

        UserDatabase.getInstance(this).userDao().updateUser(mUser)
        Toast.makeText(this, "Update successfully", Toast.LENGTH_SHORT).show()

        val intentResult =Intent()
        setResult(Activity.RESULT_OK, intentResult)
        finish()
    }
}