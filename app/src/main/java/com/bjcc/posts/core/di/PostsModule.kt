package com.bjcc.posts.core.di

import com.bjcc.posts.features.posts.data.datasource.local.PostsLocalDataSource
import com.bjcc.posts.features.posts.data.datasource.local.PostsLocalDataSourceImpl
import com.bjcc.posts.features.posts.data.datasource.remote.PostsRemoteDataSource
import com.bjcc.posts.features.posts.data.datasource.remote.PostsRemoteDataSourceImpl
import com.bjcc.posts.features.posts.data.repository.PostsRepositoryImpl
import com.bjcc.posts.features.posts.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PostsModule {

    @Binds
    abstract fun bindPostsRemoteDataSource(
        postsRemoteDataSourceImpl: PostsRemoteDataSourceImpl
    ): PostsRemoteDataSource

    @Binds
    abstract fun bindPostsLocalDataSource(
        postsLocalDataSourceImpl: PostsLocalDataSourceImpl
    ): PostsLocalDataSource

    @Binds
    abstract fun bindPostsRepository(
        postsRepositoryImpl: PostsRepositoryImpl
    ): PostsRepository
}