package com.example.sharedpreferenceandroid.model

class User(val id: Int, val name: String, val address: String){
    override fun toString(): String {
        return "User(id=$id, name='$name', address='$address')"
    }
}