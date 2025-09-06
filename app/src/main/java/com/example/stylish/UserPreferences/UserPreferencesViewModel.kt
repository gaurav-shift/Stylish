package com.example.stylish.UserPreferences

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylish.DomainLayer.model.UserPreferencesState
import com.example.stylish.DomainLayer.usecase.GetUserPreferenceUseCase
import com.example.stylish.DomainLayer.usecase.SetUserPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class  UserPreferencesViewModel(
    private val getUserPreferenceUseCase: GetUserPreferenceUseCase,
    private  val setUserPreferenceUseCase: SetUserPreferenceUseCase
) : ViewModel(){
    private  val _state = MutableStateFlow(UserPreferencesState())
    val state: StateFlow<UserPreferencesState> =  _state.asStateFlow()

    init {
        observeUserPreference()
    }
    private fun observeUserPreference(){
        viewModelScope.launch {
            combine( //dono ka state pe najre gadaye hue hai jaisi hi state change hoga niche bata dega
                getUserPreferenceUseCase.isFirstTimeLogin(),
                getUserPreferenceUseCase.isLoggedIn()
            ){
                isFirstTime,isLoggedIn ->
                Log.d("UserPreferenceViewModel","DataStore values changed- isFirstTime :$isFirstTime")
                UserPreferencesState(
                    IsFirstTimeLogin = isFirstTime,
                    isLoggedIn=isLoggedIn,
                    isLoading = false //When splash screen over just false isloading state
                ) // iska yahi kam hai ki jaise hi koi state change hoga so ye uska ek obj bana dega
            }.collect { newState->
                Log.d("UserPreferenceViewModel","Updating State-isFirstTime : ${newState.IsFirstTimeLogin}")
                _state.value = newState // collect kyakarega ki jo bhi changes hong unhe collect karke state me update kardega
            }
        }
    }

}