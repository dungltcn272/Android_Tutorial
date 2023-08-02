package com.example.roomdatabaseandroidtutorial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdatabaseandroidtutorial.User


@Database(entities = [User::class], version = 2)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        private val migration_from_1_to_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN year TEXT NOT NULL DEFAULT ''")
            }
        }

        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).allowMainThreadQueries().addMigrations(migration_from_1_to_2).build()
            }
            return instance!!
        }
    }

    abstract fun userDao(): UserDAO
}
