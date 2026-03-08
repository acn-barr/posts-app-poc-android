package com.bjcc.posts.features.posts.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bjcc.posts.R
import com.bjcc.posts.features.favoriteposts.domain.repository.FavoritePostsRepository
import com.bjcc.posts.features.login.domain.repository.UserRepository
import com.bjcc.posts.features.posts.domain.entity.toFavoritePost
import com.bjcc.posts.features.posts.domain.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val postsRepository: PostsRepository,
    private val favoritePostsRepository: FavoritePostsRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PostsState())
    val state: LiveData<PostsState> = _state.asLiveData()

    init {
        _state.update { it.copy(isLoading = true) }
        fetchFavorites()
        fetchPosts()
    }

    fun fetchFavorites() {
        viewModelScope.launch {
            favoritePostsRepository.fetchFavoritePosts().collect { favoritePosts ->
                _state.update { it.copy(favoritePosts = favoritePosts) }
            }
        }
    }

    fun fetchPosts() {
        viewModelScope.launch {
            postsRepository.fetchPosts().collect { posts ->
                _state.update {
                    if (posts.isEmpty()) {
                        it.copy(
                            isLoading = false,
                            error = context.getString(R.string.no_available_posts)
                        )
                    } else {
                        it.copy(isLoading = false, posts = posts)
                    }
                }
            }
        }
    }

    fun onToggleFavorite(postId: Int) {
        viewModelScope.launch {
            val post = _state.value.posts.find { it.id == postId }!!
            val favoritePost = _state.value.favoritePosts.find { it.id == post.id }

            if (favoritePost != null) {
                favoritePostsRepository.removeFavoritePost(favoritePost.id)
            } else {
                favoritePostsRepository.addFavoritePost(post.toFavoritePost())
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
            postsRepository.deleteAllPosts()
            favoritePostsRepository.deleteAllFavoritePosts()
        }
    }
}