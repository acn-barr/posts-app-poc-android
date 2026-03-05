package com.bjcc.posts.features.login.domain.repository

import com.bjcc.posts.features.login.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser() : Flow<User?>
    suspend fun save(user: User)
}