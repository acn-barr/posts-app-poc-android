package com.bjcc.posts.features.favoriteposts.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bjcc.posts.features.favoriteposts.domain.entity.FavoritePost
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePostDao {
    @Query("Select * FROM favoritepost")
    fun fetchAll(): Flow<List<FavoritePost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoritePost: FavoritePost)

    @Query("DELETE FROM favoritepost WHERE id = :favoritePostId")
    suspend fun remove(favoritePostId: Int)

    @Query("DELETE FROM favoritepost")
    suspend fun deleteAll()
}