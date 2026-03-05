package com.bjcc.posts.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bjcc.posts.features.login.data.datasource.local.dao.UserDao
import com.bjcc.posts.features.login.domain.entity.User

@Database(
    entities = [
        User::class,
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object{
        const val DATABASE_NAME = "app_db"
    }
}