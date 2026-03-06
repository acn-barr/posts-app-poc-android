package com.bjcc.posts.core.di

import com.bjcc.posts.features.login.data.datasource.local.UserLocalDataSource
import com.bjcc.posts.features.login.data.datasource.local.UserLocalDataSourceImpl
import com.bjcc.posts.features.login.data.repository.UserRepositoryImpl
import com.bjcc.posts.features.login.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {

    @Binds
    abstract fun bindUserLocalDataSource(
        userLocalDataSourceImpl: UserLocalDataSourceImpl
    ): UserLocalDataSource

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}