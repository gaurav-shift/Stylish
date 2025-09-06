package com.example.stylish.DomainLayer.usecase

import com.example.stylish.DomainLayer.repository.UserPreferencesRepository

class SetUserPreferenceUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
){
    suspend fun setFirstTimeLogin(isFirstTime: Boolean){
        userPreferencesRepository.setFirstTimeLogin(isFirstTime)
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean){
        userPreferencesRepository.setLoggedin(isLoggedIn)
    }

}