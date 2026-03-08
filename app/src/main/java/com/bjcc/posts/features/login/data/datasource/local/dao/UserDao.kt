package com.bjcc.posts.features.login.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bjcc.posts.features.login.domain.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("Select * FROM user LIMIT 1")
    fun fetchUser(): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
}