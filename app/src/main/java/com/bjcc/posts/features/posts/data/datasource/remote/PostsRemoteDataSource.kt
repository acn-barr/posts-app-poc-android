package com.bjcc.posts.features.posts.data.datasource.remote

import android.util.Log
import com.bjcc.posts.core.api.ApiService
import com.bjcc.posts.features.posts.domain.entity.Post
import javax.inject.Inject

interface PostsRemoteDataSource {
    suspend fun getPosts(): List<Post>
}

class PostsRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : PostsRemoteDataSource {
    companion object {
        private const val TAG = "PostsRemoteDataSourceImpl"
    }

    override suspend fun getPosts(): List<Post> {
        val response = apiService.fetchPosts()

        if (response.isSuccessful && response.body() != null) {
            Log.d(TAG, "isSuccessful")
            return response.body()!!.map { postItem ->
                Post(
                    id = postItem.id,
                    userId = postItem.userId,
                    title = postItem.title,
                    body = postItem.body
                )
            }.toList()
        }
        Log.e(TAG, "Error occurred here")
        return emptyList()
    }
}
