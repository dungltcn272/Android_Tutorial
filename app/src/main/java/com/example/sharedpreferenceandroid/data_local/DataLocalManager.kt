package com.example.sharedpreferenceandroid.data_local

import android.content.Context
import com.example.sharedpreferenceandroid.model.User
import com.google.gson.Gson
import org.json.JSONArray

class DataLocalManager {
    private lateinit var mySharePreference: MySharePreference
    companion object{
        private const val PREF_OBJECT_USER="PREF_OBJECT_USER"
        private const val PREF_LIST_USER="PREF_LIST_USER"
        private var instance: DataLocalManager? = null
        fun init(context: Context){
            instance = DataLocalManager()
            instance!!.mySharePreference= MySharePreference(context)
        }
        private fun getInstance(): DataLocalManager {
            if(instance ==null){
                instance = DataLocalManager()
            }
            return instance!!
        }

        fun setUser(user: User){
            val gson = Gson()
            val strJsonUser=gson.toJson(user)
            getInstance().mySharePreference.putStringValue(PREF_OBJECT_USER, strJsonUser)

        }

        fun getUser(): User {
            val strJsonUser = getInstance().mySharePreference.getStringValue(PREF_OBJECT_USER)
            val gson = Gson()
            return gson.fromJson(strJsonUser, User::class.java) as User
        }

        fun setListUser(listUser: List<User>){
            val gson=Gson()
            val jsonArray=gson.toJsonTree(listUser).asJsonArray
            val strJsonArray=jsonArray.toString()
            getInstance().mySharePreference.putStringValue(PREF_LIST_USER, strJsonArray)
        }

        fun getListUser(): List<User>{
            val strJsonArray= getInstance().mySharePreference.getStringValue(PREF_LIST_USER)
            val listUser =ArrayList<User>()
            val jsonArray =JSONArray(strJsonArray)
            val gson=Gson()
            for(i in 0 until jsonArray.length()){
                val jsonObject=jsonArray.getJSONObject(i)
                val user=gson.fromJson(jsonObject.toString(),User::class.java)
                listUser.add(user)
            }
            return listUser
        }
    }
}