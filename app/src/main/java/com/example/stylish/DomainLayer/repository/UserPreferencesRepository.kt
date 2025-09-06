package com.example.stylish.DomainLayer.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val isFirstTimeLogin: Flow<Boolean>
    val isLoggedIn: Flow<Boolean>
    suspend fun setFirstTimeLogin(isFirstTime: Boolean)
    suspend fun setLoggedin(isLoggedIn: Boolean)
}