package com.bjcc.posts.features.posts.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bjcc.posts.features.posts.domain.entity.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("Select * FROM post")
    fun fetchAll(): Flow<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: List<Post>)

    @Query("DELETE FROM post")
    suspend fun deleteAll()
}