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
        private const val TAG = "PostsRemoteDataSourceImpl"
    }

    override suspend fun fetchPosts(): Flow<List<Post>> {
        try {
            val remotePosts = postsRemoteDataSource.fetchPosts()

            postsLocalDataSource.deleteAllPosts()
            postsLocalDataSource.savePosts(remotePosts)

            return postsLocalDataSource.fetchPosts()
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.localizedMessage}")
            return postsLocalDataSource.fetchPosts()
        }
    }

    override suspend fun deleteAllPosts() =
        postsLocalDataSource.deleteAllPosts()
}