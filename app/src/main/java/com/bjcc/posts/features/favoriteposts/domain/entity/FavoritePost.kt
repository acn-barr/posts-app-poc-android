package com.bjcc.posts.features.favoriteposts.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoritePost(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String,
)

