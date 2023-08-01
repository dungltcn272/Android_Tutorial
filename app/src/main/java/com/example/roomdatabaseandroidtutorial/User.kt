package com.example.roomdatabaseandroidtutorial

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
class User(var username: String,var address: String) : java.io.Serializable{

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

}