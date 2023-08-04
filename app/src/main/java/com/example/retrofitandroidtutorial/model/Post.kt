package com.example.retrofitandroidtutorial.model

class Post(val userId: Int, val id: Int, val title: String, val body: String) {
    override fun toString(): String {
        return "Post(userId=$userId, id=$id, title='$title', body='$body')"
    }
}