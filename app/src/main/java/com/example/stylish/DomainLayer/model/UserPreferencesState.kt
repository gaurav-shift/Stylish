package com.example.stylish.DomainLayer.model

data class UserPreferencesState(
    val IsFirstTimeLogin: Boolean = true,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = true
)
