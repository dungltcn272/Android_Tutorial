package com.example.roomdatabaseandroidtutorial

import com.example.roomdatabaseandroidtutorial.database.UserDatabase
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var edtUsername: EditText
    private lateinit var edtAddress: EditText
    private lateinit var btnAddUser: Button
    private lateinit var rcvUser: RecyclerView
    private lateinit var tvDeleteAll : TextView
    private lateinit var edtSearch: EditText

    private lateinit var userAdapter: UserAdapter
    private lateinit var mListUser: List<User>

    companion object {
        const val MY_REQUEST_CODE = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()

        userAdapter = UserAdapter(object : UserAdapter.IClickItemUser {
            override fun updateUser(user: User) {
                clickUpdateUser(user)
            }

            override fun deleteUser(user: User) {
                clickDeleteUser(user)
            }
        })
        mListUser = ArrayList()
        userAdapter.setData(mListUser)

        val linearLayoutManager = LinearLayoutManager(this)
        rcvUser.layoutManager = linearLayoutManager

        rcvUser.adapter = userAdapter

        btnAddUser.setOnClickListener {
            addUser()
        }

        tvDeleteAll.setOnClickListener{
            clickDeleteAllUser()
        }

        edtSearch.setOnEditorActionListener(object :TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId==EditorInfo.IME_ACTION_SEARCH){
                    handleSearchUser()
                }
                return false
            }

        })

        loadData()
    }

    private fun addUser() {
        val strUsername = edtUsername.text.toString().trim()
        val strAddress = edtAddress.text.toString().trim()

        if (TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)) {
            return
        }
        val user = User(strUsername, strAddress)

        if (isUserExist(user)) {
            Toast.makeText(this, "User exist", Toast.LENGTH_SHORT).show()
            return
        }
        UserDatabase.getInstance(this).userDao().insertUser(user)
        Toast.makeText(this, "Add user successfully", Toast.LENGTH_SHORT).show()

        edtUsername.setText("")
        edtAddress.setText("")
        hideSoftKeyboard()

        loadData()

    }

    private fun loadData() {
        mListUser = UserDatabase.getInstance(this).userDao().getListUser()
        userAdapter.setData(mListUser)
    }

    private fun isUserExist(user: User): Boolean {
        val list: List<User> =
            UserDatabase.getInstance(this).userDao().checkUser(username = user.username)
        return list != null && list.isNotEmpty()
    }

    private fun initUi() {
        edtUsername = findViewById(R.id.edit_username)
        edtAddress = findViewById(R.id.edit_address)
        btnAddUser = findViewById(R.id.btn_add_user)
        rcvUser = findViewById(R.id.rcv_user)
        tvDeleteAll=findViewById(R.id.tv_delete_all)
        edtSearch=findViewById(R.id.edt_search)
    }

    private fun hideSoftKeyboard() {
        try {
            val inputMethodManager =
                getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        } catch (ex: java.lang.NullPointerException) {
            ex.printStackTrace()
        }
    }

    private fun clickUpdateUser(user: User) {
        val intent = Intent(this, UpdateActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("object_user", user)
        intent.putExtras(bundle)
        startActivityForResult(intent, MY_REQUEST_CODE)
    }

    private fun clickDeleteUser(user: User) {
        AlertDialog.Builder(this@MainActivity).setTitle("Confirm delete user").setMessage("Are you sure?")
            .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    UserDatabase.getInstance(this@MainActivity).userDao().deleteUser(user)
                    Toast.makeText(this@MainActivity, "Delete user successfully", Toast.LENGTH_SHORT).show()

                    loadData()
                }
            }).setNegativeButton("No", null).show()
    }
    private fun clickDeleteAllUser() {
        AlertDialog.Builder(this@MainActivity).setTitle("Confirm delete all user").setMessage("Are you sure?")
            .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    UserDatabase.getInstance(this@MainActivity).userDao().deleteAllUser()
                    Toast.makeText(this@MainActivity, "Delete all user successfully", Toast.LENGTH_SHORT).show()

                    loadData()
                }
            }).setNegativeButton("No", null).show()
    }

    private fun handleSearchUser() {
        val strKeyword=edtSearch.text.toString().trim()
        mListUser=ArrayList()
        mListUser =UserDatabase.getInstance(this@MainActivity).userDao().searchUser(strKeyword)
        userAdapter.setData(mListUser)
        hideSoftKeyboard()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadData()
        }
    }
}