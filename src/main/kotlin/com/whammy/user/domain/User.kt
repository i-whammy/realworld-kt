package com.whammy.user.domain

data class User(val email: String, val password: String, val token: String) {
    fun refreshToken(): User {
        return User(email, password, token + "a")
    }
}