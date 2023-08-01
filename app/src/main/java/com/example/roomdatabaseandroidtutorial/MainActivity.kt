package com.example.roomdatabaseandroidtutorial

import com.example.roomdatabaseandroidtutorial.database.UserDatabase
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var edtUsername : EditText
    private lateinit var edtAddress : EditText
    private lateinit var btnAddUser : Button
    private lateinit var rcvUser : RecyclerView

    private lateinit var userAdapter: UserAdapter
    private lateinit var mListUser: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()

        userAdapter= UserAdapter()
        mListUser=ArrayList()
        userAdapter.setData(mListUser)

        val linearLayoutManager =LinearLayoutManager(this)
        rcvUser.layoutManager=linearLayoutManager

        rcvUser.adapter=userAdapter

        btnAddUser.setOnClickListener {
            addUser()
        }
    }

    private fun addUser() {
        val strUsername =edtUsername.text.toString().trim()
        val strAddress =edtAddress.text.toString().trim()

        if(TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)){
            return
        }
        val user= User(strUsername, strAddress)
        UserDatabase.getInstance(this).userDao().insertUser(user)
        Toast.makeText(this, "Add user successfully", Toast.LENGTH_SHORT).show()

        edtUsername.setText("")
        edtAddress.setText("")
        hideSoftKeyboard()

        mListUser= UserDatabase.getInstance(this).userDao().getListUser()
        userAdapter.setData(mListUser)
    }

    private fun initUi(){
        edtUsername=findViewById(R.id.edit_username)
        edtAddress=findViewById(R.id.edit_address)
        btnAddUser=findViewById(R.id.btn_add_user)
        rcvUser=findViewById(R.id.rcv_user)
    }

    private fun hideSoftKeyboard(){
        try{
            val inputMethodManager =getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }catch (ex: java.lang.NullPointerException){
            ex.printStackTrace()
        }
    }
}