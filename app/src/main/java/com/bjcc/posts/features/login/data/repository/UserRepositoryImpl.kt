package com.bjcc.posts.features.login.data.repository

import android.util.Log
import com.bjcc.posts.features.login.data.datasource.local.UserLocalDataSource
import com.bjcc.posts.features.login.domain.entity.User
import com.bjcc.posts.features.login.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
) : UserRepository {
    companion object {
        private const val TAG = "UserRepositoryImpl"
    }

    override suspend fun getUser(): Flow<User?> {
        return userLocalDataSource.getUser();
    }

    override suspend fun save(user: User) {
        // TODO: Return RESULT for better response!!!
        val result = userLocalDataSource.save(user)
        Log.d(TAG, "saveUser result: $result")
    }
}