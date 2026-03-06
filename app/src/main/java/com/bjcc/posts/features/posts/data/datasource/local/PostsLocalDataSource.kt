package com.bjcc.posts.features.posts.data.datasource.local

import com.bjcc.posts.core.database.AppDatabase
import com.bjcc.posts.features.posts.domain.entity.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PostsLocalDataSource {
    suspend fun getPosts(): Flow<List<Post>>
    suspend fun savePosts(posts: List<Post>)
}

class PostsLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : PostsLocalDataSource {

    override suspend fun getPosts(): Flow<List<Post>> {
        return appDatabase.postDao().getPosts()
    }

    override suspend fun savePosts(posts: List<Post>) {
        return appDatabase.postDao().savePosts(posts)
    }
}
