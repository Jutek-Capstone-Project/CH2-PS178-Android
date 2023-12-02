package com.bangkit.ch2_ps178_android.data.model

data class UserModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)