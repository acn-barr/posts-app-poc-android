package com.bjcc.posts.features.login.data.datasource.local

import com.bjcc.posts.core.database.AppDatabase
import com.bjcc.posts.features.login.domain.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserLocalDataSource {
    suspend fun getUser() : Flow<User?>
    suspend fun save(user: User) : Long
}

class UserLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
): UserLocalDataSource {

    override suspend fun getUser(): Flow<User?> {
        return appDatabase.userDao().getUser()
    }

    override suspend fun save(user: User): Long {
        return appDatabase.userDao().saveUser(user)
    }
}
