package com.bjcc.posts.features.posts.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bjcc.posts.features.favoriteposts.domain.entity.FavoritePost

@Entity
data class Post(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String,
    var isFavorite: Boolean = false,
)

fun Post.toFavoritePost() = FavoritePost(
    id = id,
    title = title,
    body = body
)