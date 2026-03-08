package com.bjcc.posts.features.favoriteposts.domain.repository

import com.bjcc.posts.features.favoriteposts.domain.entity.FavoritePost
import kotlinx.coroutines.flow.Flow

interface FavoritePostsRepository {
    suspend fun fetchFavoritePosts(): Flow<List<FavoritePost>>
    suspend fun addFavoritePost(favoritePost: FavoritePost)
    suspend fun removeFavoritePost(favoritePostId: Int)
}