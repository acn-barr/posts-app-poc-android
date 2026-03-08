package com.bjcc.posts.features.posts.data.datasource.remote

import com.bjcc.posts.core.api.ApiService
import com.bjcc.posts.features.posts.domain.entity.Post
import javax.inject.Inject

interface PostsRemoteDataSource {
    suspend fun fetchPosts(): List<Post>
}

class PostsRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : PostsRemoteDataSource {

    companion object {
        private const val TAG = "PostsRemoteDataSourceImpl"
    }

    override suspend fun fetchPosts(): List<Post> {
        val response = apiService.fetchPosts()

        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.map { postItem ->
                Post(
                    id = postItem.id,
                    title = postItem.title,
                    body = postItem.body
                )
            }.toList()
        } else {
            emptyList()
        }
    }
}
