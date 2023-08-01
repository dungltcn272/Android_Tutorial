package com.example.roomdatabaseandroidtutorial.database

import androidx.room.*
import com.example.roomdatabaseandroidtutorial.User

@Dao
interface UserDAO {

    @Insert
    fun insertUser(user : User)

    @Query("SELECT * FROM user")
    fun getListUser() : List<User>

    @Query("SELECT * FROM user where username= :username")
    fun checkUser(username: String) : List<User>
    @Update
    fun updateUser(user: User)
    @Delete
    fun deleteUser(user: User)
    @Query("DELETE FROM user")
    fun deleteAllUser()
    @Query("SELECT * FROM user WHERE username LIKE '%' || :name || '%'")
    fun searchUser(name: String): List<User>
}