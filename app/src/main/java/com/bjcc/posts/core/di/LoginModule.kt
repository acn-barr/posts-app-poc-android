package com.bjcc.posts.core.di

import com.bjcc.posts.features.login.data.datasource.local.UserLocalDataSource
import com.bjcc.posts.features.login.data.datasource.local.UserLocalDataSourceImpl
import com.bjcc.posts.features.login.data.repository.UserRepositoryImpl
import com.bjcc.posts.features.login.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginModule {
//object LoginModule {

    @Binds
    abstract fun bindUserLocalDataSource(
        userLocalDataSourceImpl: UserLocalDataSourceImpl
    ): UserLocalDataSource

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

//    @Provides
//    @Singleton // Or the appropriate scope
//    fun provideLoginRepository(): LoginRepository {
//        return LoginRepositoryImpl()
//    }
}