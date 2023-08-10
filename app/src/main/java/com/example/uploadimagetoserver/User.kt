package com.example.uploadimagetoserver

import com.google.gson.annotations.SerializedName

class User(private val  account: Int, private val username: String, private val password: String,@SerializedName("avt")  private val avt: String)