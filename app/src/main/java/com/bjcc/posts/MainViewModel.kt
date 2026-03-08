package com.bjcc.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bjcc.posts.features.login.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isUserLoggedIn: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isUserLoggedIn: LiveData<Boolean?> = _isUserLoggedIn.asLiveData()

    init {
        viewModelScope.launch {
            userRepository.fetchUser().collect { user ->
                _isUserLoggedIn.update { user != null }
            }
        }
    }
}