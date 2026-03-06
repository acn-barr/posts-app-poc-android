package com.bjcc.posts.features.posts.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bjcc.posts.R
import com.bjcc.posts.features.posts.domain.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val postsRepository: PostsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PostsState())
    val state: LiveData<PostsState> = _state.asLiveData()

    init {
        viewModelScope.launch {
            // loading
            _state.update { it.copy(isLoading = true) }

            withContext(Dispatchers.IO) {
                postsRepository.getPosts()
            }.collect { posts ->
                // success
                _state.update {
                    // check posts
                    val errorMessage = if (posts.isEmpty()) {
                        context.getString(R.string.app_default_error_message)
                    } else null

                    it.copy(
                        isLoading = false,
                        posts = posts,
                        error = errorMessage
                    )
                }
            }
        }
    }
}