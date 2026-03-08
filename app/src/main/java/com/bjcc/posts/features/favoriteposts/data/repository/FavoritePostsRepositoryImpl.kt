package com.bjcc.posts.features.favoriteposts.data.repository

import com.bjcc.posts.features.favoriteposts.data.datasource.local.FavoritePostsLocalDataSource
import com.bjcc.posts.features.favoriteposts.domain.entity.FavoritePost
import com.bjcc.posts.features.favoriteposts.domain.repository.FavoritePostsRepository
import javax.inject.Inject

class FavoritePostsRepositoryImpl @Inject constructor(
    private val favoritePostsLocalDataSource: FavoritePostsLocalDataSource,
) : FavoritePostsRepository {

    override suspend fun fetchFavoritePosts() =
        favoritePostsLocalDataSource.fetchFavoritePosts()

    override suspend fun addFavoritePost(favoritePost: FavoritePost) =
        favoritePostsLocalDataSource.addFavoritePost(favoritePost)

    override suspend fun removeFavoritePost(favoritePostId: Int) =
        favoritePostsLocalDataSource.removeFavoritePost(favoritePostId)

    override suspend fun deleteAllFavoritePosts() =
        favoritePostsLocalDataSource.deleteAllFavoritePosts()
}