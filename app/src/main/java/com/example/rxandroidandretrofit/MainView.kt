package com.example.rxandroidandretrofit

import com.example.rxandroidandretrofit.model.ObjectData

interface MainView {
    fun loginSuccess(listData: List<ObjectData>)
    fun loginError()
}