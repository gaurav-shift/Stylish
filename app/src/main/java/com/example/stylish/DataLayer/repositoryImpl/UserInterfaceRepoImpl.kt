package com.example.stylish.DataLayer.repositoryImpl

import com.example.stylish.DataLayer.local.UserPreferenceDataStore
import com.example.stylish.DomainLayer.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class UserInterfaceRepoImpl (
    private  val userPreferenceDataStore : UserPreferenceDataStore) : UserPreferencesRepository{

    override val isFirstTimeLogin: Flow<Boolean> = userPreferenceDataStore.isFirstTimeLogin

    override val isLoggedIn: Flow<Boolean> = userPreferenceDataStore.isLoggedIn

    override suspend fun setFirstTimeLogin(isFirstTime: Boolean) {
        userPreferenceDataStore.setFirstTimeLogin(isFirstTime)
    }

    override suspend fun setLoggedin(isLoggedIn: Boolean) {
        userPreferenceDataStore.setLoggedin(isLoggedIn)
    }

}