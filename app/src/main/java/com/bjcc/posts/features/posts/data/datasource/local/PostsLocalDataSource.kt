package com.bjcc.posts.features.posts.data.datasource.local

import com.bjcc.posts.core.database.AppDatabase
import com.bjcc.posts.features.posts.domain.entity.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PostsLocalDataSource {
    suspend fun fetchPosts(): Flow<List<Post>>
    suspend fun savePosts(posts: List<Post>)
    suspend fun deleteAllPosts()
}

class PostsLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : PostsLocalDataSource {

    override suspend fun fetchPosts() =
        appDatabase.postDao().fetchPosts()

    override suspend fun savePosts(posts: List<Post>) =
        appDatabase.postDao().insertPosts(posts)

    override suspend fun deleteAllPosts() =
        appDatabase.postDao().deleteAllPosts()
}
