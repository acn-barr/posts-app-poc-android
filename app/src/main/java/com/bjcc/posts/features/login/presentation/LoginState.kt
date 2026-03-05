package com.bjcc.posts.features.login.presentation

data class LoginState(
    var isLoginButtonEnabled: Boolean = false,
    var shouldNavigateToPosts: Boolean = false,
    // TODO: Add loading?
)
