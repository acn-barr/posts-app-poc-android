package com.bjcc.posts.features.favoriteposts.data.datasource.local

import com.bjcc.posts.core.database.AppDatabase
import com.bjcc.posts.features.favoriteposts.domain.entity.FavoritePost
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavoritePostsLocalDataSource {
    suspend fun fetchFavoritePosts(): Flow<List<FavoritePost>>
    suspend fun addFavoritePost(favoritePost: FavoritePost)
    suspend fun removeFavoritePost(favoritePostId: Int)
    suspend fun deleteAllFavoritePosts()
}

class FavoritePostsLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : FavoritePostsLocalDataSource {

    override suspend fun fetchFavoritePosts() =
        appDatabase.favoritePostDao().fetchAll()

    override suspend fun addFavoritePost(favoritePost: FavoritePost) =
        appDatabase.favoritePostDao().insert(favoritePost)

    override suspend fun removeFavoritePost(favoritePostId: Int) =
        appDatabase.favoritePostDao().remove(favoritePostId)

    override suspend fun deleteAllFavoritePosts() =
        appDatabase.favoritePostDao().deleteAll()

}
