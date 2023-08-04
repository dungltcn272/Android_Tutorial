package com.example.checkappfirstinstallandroid

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences (var mContext : Context) {
    companion object{
        const val MY_SHARED_PREFERENCES="MY_SHARED_PREFERENCES"
    }

    fun putBooleanValue(key: String, value : Boolean){
        val sharedPreferences =mContext.getSharedPreferences(MY_SHARED_PREFERENCES, 0)
        val editor : SharedPreferences.Editor =sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBooleanValue(key: String): Boolean{
        val sharedPreferences =mContext.getSharedPreferences(MY_SHARED_PREFERENCES, 0)
        return sharedPreferences.getBoolean(key, false)
    }


}