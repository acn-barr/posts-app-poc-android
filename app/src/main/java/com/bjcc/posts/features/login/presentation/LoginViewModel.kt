package com.bjcc.posts.features.login.presentation

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bjcc.posts.features.login.domain.entity.User
import com.bjcc.posts.features.login.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _state = MutableStateFlow(LoginState())
    val state: LiveData<LoginState> = _state.asLiveData()

    fun login() {
        viewModelScope.launch {
            userRepository.saveUser(User(_email.value, _password.value))
        }
        _state.update {
            it.copy(shouldNavigateToPosts = true)
        }
    }

    fun onEmailUpdate(email: String) {
        _email.update { email }
        _state.update {
            it.copy(isLoginButtonEnabled = isEmailValid() && isPasswordValid())
        }
    }

    fun onPasswordUpdate(password: String) {
        _password.update { password }
        _state.update {
            it.copy(isLoginButtonEnabled = isEmailValid() && isPasswordValid())
        }
    }

    private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()
    }

    private fun isPasswordValid() =
        _password.value.length in 8..15
}