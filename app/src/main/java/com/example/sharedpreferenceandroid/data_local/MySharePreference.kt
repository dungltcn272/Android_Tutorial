package com.example.sharedpreferenceandroid.data_local

import android.content.Context

class MySharePreference (private val mContext: Context){
    companion object{
        const val MY_SHARE_PREFERENCE="MY_SHARE_PREFERENCE"
    }

    fun putStringValue(key: String, value: String){
        val sharePreference =mContext.getSharedPreferences(MY_SHARE_PREFERENCE,Context.MODE_PRIVATE)
        val editor =sharePreference.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringValue (key: String): String{
        val sharePreference =mContext.getSharedPreferences(MY_SHARE_PREFERENCE,Context.MODE_PRIVATE)
        return sharePreference.getString(key,"").toString()
    }

}