package com.bjcc.posts.core.api

import com.bjcc.posts.features.posts.data.datasource.model.PostResponseItemModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun fetchPosts(): Response<List<PostResponseItemModel>>
}