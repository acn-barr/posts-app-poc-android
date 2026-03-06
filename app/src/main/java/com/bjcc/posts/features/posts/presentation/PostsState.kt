package com.bjcc.posts.features.posts.presentation

import com.bjcc.posts.features.posts.domain.entity.Post

data class PostsState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String? = null,
)
