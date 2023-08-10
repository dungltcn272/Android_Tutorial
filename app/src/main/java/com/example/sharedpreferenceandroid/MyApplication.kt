package com.example.sharedpreferenceandroid

import android.app.Application
import com.example.sharedpreferenceandroid.data_local.DataLocalManager

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DataLocalManager.init(applicationContext)
    }
}