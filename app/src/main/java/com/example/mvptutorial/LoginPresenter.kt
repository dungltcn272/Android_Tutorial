package com.example.mvptutorial

class LoginPresenter(private var mLoginInterface: LoginInterface) {

    fun login(user : User){
        if(user.isValidEmail() && user.isValidPassword()){
            mLoginInterface.loginSuccess()
        }else{
            mLoginInterface.loginError()
        }
    }
}