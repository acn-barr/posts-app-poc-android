package com.bjcc.posts.features.posts.data.repository

import android.util.Log
import com.bjcc.posts.features.posts.data.datasource.local.PostsLocalDataSource
import com.bjcc.posts.features.posts.data.datasource.remote.PostsRemoteDataSource
import com.bjcc.posts.features.posts.domain.entity.Post
import com.bjcc.posts.features.posts.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsRemoteDataSource: PostsRemoteDataSource,
    private val postsLocalDataSource: PostsLocalDataSource,
) : PostsRepository {
    companion object {
        private const val TAG = "PostsRepositoryImpl"
    }

    override suspend fun getPosts(): Flow<List<Post>> {
        val posts = postsRemoteDataSource.getPosts()
        postsLocalDataSource.savePosts(posts)
        return postsLocalDataSource.getPosts()
    }
}