package com.bjcc.posts.features.login.presentation

data class LoginState(
    val isLoginButtonEnabled: Boolean = false,
    val shouldNavigateToPosts: Boolean = false,
)
