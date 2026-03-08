package com.bjcc.posts.features.posts.domain.repository

import com.bjcc.posts.features.posts.domain.entity.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun fetchPosts() : Flow<List<Post>>
    suspend fun deleteAllPosts()
}