package com.example.roomdatabaseandroidtutorial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabaseandroidtutorial.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).allowMainThreadQueries().build()
            }
            return instance!!
        }
    }

    abstract fun userDao(): UserDAO
}
