package com.example.roomdatabaseandroidtutorial

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
class User(val username: String, val address: String){

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

}