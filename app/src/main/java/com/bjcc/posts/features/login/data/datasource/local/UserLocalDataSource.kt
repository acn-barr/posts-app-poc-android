package com.bjcc.posts.features.login.data.datasource.local

import com.bjcc.posts.core.database.AppDatabase
import com.bjcc.posts.features.login.domain.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserLocalDataSource {
    suspend fun fetchUser(): Flow<User?>
    suspend fun saveUser(user: User)

    suspend fun deleteUser()
}

class UserLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
): UserLocalDataSource {

    override suspend fun fetchUser() =
        appDatabase.userDao().fetchUser()

    override suspend fun saveUser(user: User) =
        appDatabase.userDao().insert(user)

    override suspend fun deleteUser() =
        appDatabase.userDao().delete()
}
