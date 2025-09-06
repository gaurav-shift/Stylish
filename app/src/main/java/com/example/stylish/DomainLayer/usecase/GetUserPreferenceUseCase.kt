package com.example.stylish.DomainLayer.usecase

import com.example.stylish.DomainLayer.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetUserPreferenceUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
){
    fun isFirstTimeLogin() : Flow<Boolean> = userPreferencesRepository.isFirstTimeLogin
    fun isLoggedIn() : Flow<Boolean> = userPreferencesRepository.isLoggedIn
}