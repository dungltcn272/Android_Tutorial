package com.example.mvptutorial
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() ,LoginInterface{
    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var tvMessage : TextView
    private lateinit var btnLogin: Button

    private lateinit var mLoginPresenter : LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        tvMessage=findViewById(R.id.tv_message)
        btnLogin=findViewById(R.id.btn_login)

        mLoginPresenter =LoginPresenter(this)

        btnLogin.setOnClickListener {
            clickLogin()
        }
    }

    private fun clickLogin() {
        val stringEmail =edtEmail.text.toString().trim()
        val stringPassword=edtPassword.text.toString().trim()

        val user =User(stringEmail, stringPassword)
        mLoginPresenter.login(user)
    }

    override fun loginSuccess() {
        tvMessage.visibility= View.VISIBLE
        tvMessage.text="Login Success"
        tvMessage.setTextColor(resources.getColor(R.color.green))
    }

    override fun loginError() {
        tvMessage.visibility= View.VISIBLE
        tvMessage.text="Email or password invalid"
        tvMessage.setTextColor(resources.getColor(R.color.red))
    }
}