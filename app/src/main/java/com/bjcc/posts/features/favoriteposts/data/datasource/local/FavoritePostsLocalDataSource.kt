package com.bjcc.posts.features.favoriteposts.data.datasource.local

import com.bjcc.posts.core.database.AppDatabase
import com.bjcc.posts.features.favoriteposts.domain.entity.FavoritePost
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavoritePostsLocalDataSource {
    suspend fun fetchFavoritePosts(): Flow<List<FavoritePost>>
    suspend fun addFavoritePost(favoritePost: FavoritePost)
    suspend fun removeFavoritePost(favoritePostId: Int)
}

class FavoritePostsLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : FavoritePostsLocalDataSource {

    override suspend fun fetchFavoritePosts() =
        appDatabase.favoritePostDao().fetchFavoritePosts()

    override suspend fun addFavoritePost(favoritePost: FavoritePost) =
        appDatabase.favoritePostDao().insertFavoritePost(favoritePost)

    override suspend fun removeFavoritePost(favoritePostId: Int) =
        appDatabase.favoritePostDao().removeFavoritePost(favoritePostId)

}
