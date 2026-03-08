package com.bjcc.posts.features.login.data.datasource.local

import com.bjcc.posts.core.database.AppDatabase
import com.bjcc.posts.features.login.domain.entity.User
import javax.inject.Inject

interface UserLocalDataSource {
    suspend fun fetchUser(): User?
    suspend fun saveUser(user: User)
}

class UserLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
): UserLocalDataSource {

    override suspend fun fetchUser(): User? =
        appDatabase.userDao().fetchUser()

    override suspend fun saveUser(user: User) =
        appDatabase.userDao().insertUser(user)
}
