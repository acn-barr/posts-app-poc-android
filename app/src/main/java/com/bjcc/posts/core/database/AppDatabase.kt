package com.bjcc.posts.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bjcc.posts.features.login.data.datasource.local.dao.UserDao
import com.bjcc.posts.features.login.domain.entity.User
import com.bjcc.posts.features.posts.data.datasource.local.dao.PostDao
import com.bjcc.posts.features.posts.domain.entity.Post

@Database(
    entities = [
        User::class,
        Post::class,
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun postDao() : PostDao

    companion object{
        const val DATABASE_NAME = "app_db"
    }
}