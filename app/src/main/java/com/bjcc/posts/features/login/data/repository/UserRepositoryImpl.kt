package com.bjcc.posts.features.login.data.repository

import com.bjcc.posts.features.login.data.datasource.local.UserLocalDataSource
import com.bjcc.posts.features.login.domain.entity.User
import com.bjcc.posts.features.login.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
) : UserRepository {

    override suspend fun fetchUser() =
        userLocalDataSource.fetchUser()

    override suspend fun saveUser(user: User) =
        userLocalDataSource.saveUser(user)

    override suspend fun logout() =
        userLocalDataSource.deleteUser()

}