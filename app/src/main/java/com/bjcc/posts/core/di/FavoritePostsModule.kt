package com.bjcc.posts.core.di

import com.bjcc.posts.features.favoriteposts.data.datasource.local.FavoritePostsLocalDataSource
import com.bjcc.posts.features.favoriteposts.data.datasource.local.FavoritePostsLocalDataSourceImpl
import com.bjcc.posts.features.favoriteposts.data.repository.FavoritePostsRepositoryImpl
import com.bjcc.posts.features.favoriteposts.domain.repository.FavoritePostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class FavoriteFavoritePostsModule {

    @Binds
    abstract fun bindFavoritePostsLocalDataSource(
        favoritePostsLocalDataSourceImpl: FavoritePostsLocalDataSourceImpl
    ): FavoritePostsLocalDataSource

    @Binds
    abstract fun bindFavoritePostsRepository(
        favoritePostsRepositoryImpl: FavoritePostsRepositoryImpl
    ): FavoritePostsRepository
}