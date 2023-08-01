package com.example.roomdatabaseandroidtutorial.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdatabaseandroidtutorial.User

@Dao
interface UserDAO {

    @Insert
    fun insertUser(user : User)

    @Query("SELECT * FROM user")
    fun getListUser() : List<User>
}