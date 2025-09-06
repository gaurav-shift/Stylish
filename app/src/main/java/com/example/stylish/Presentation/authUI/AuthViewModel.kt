package com.example.stylish.Presentation.authUI

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylish.DomainLayer.usecase.LoginUserCase
import com.example.stylish.DomainLayer.usecase.SetUserPreferenceUseCase
import com.example.stylish.DomainLayer.usecase.SignupUserUseCase
import com.example.stylish.DomainLayer.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUserCase,
    private val signupUserUseCase: SignupUserUseCase,
//    private val googleSignInUseCase: GoogleSignInUseCase,
   private val setUserPreferenceUseCase: SetUserPreferenceUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<Result<String>>(Result.Idle)
    val authState = _authState.asStateFlow()
   // val authState: StateFlow<Result<String>> = _authState or use smart tyoecasting method to change authstate type from mutablestateflow to stateflow
   fun login(email:String,password:String) {
       _authState.value =
           Result.Loading // Idle se loading state kiya jaise hi button click hoga login wala
       viewModelScope.launch(Dispatchers.IO) { // corutine ka concept use kiya taki screen laggy na ho iske bina screen flickiring karne lage ga
           // Do jagah try catch lagane ki jrurat nahi hoti hai agar repoImpl me laga diya hai so enough hai app crash nahi hoga
           val result = loginUseCase(email, password)
           _authState.value =
               result // Problem yaha pe ye hai ki _authstate jo hai vo to state ko store karega but hum pura result hi store kara
           // rahe hai cuz value jo hai vo generic type hai vo kuch bhi store kar sakta hai

           if (result is Result.Success) {
               setUserPreferenceUseCase.setLoggedIn(true)
               setUserPreferenceUseCase.setFirstTimeLogin(false)
           }

       }
   }


//       fun signInWithGoogle(account: GoogleSignInAccount) {
//           _authState.value = Result.Loading
//           viewModelScope.launch {
//               val result = googleSignInUseCase(account)
//               _authState.value = result
//           }
//       }




    fun signup(email: String, password: String) {
        _authState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = signupUserUseCase(email, password)
            if(result is Result.Success){
                setUserPreferenceUseCase.setLoggedIn(true)
                setUserPreferenceUseCase.setFirstTimeLogin(false)
            }
            _authState.value = result
        }
    }

    fun resetAuthState() {
        _authState.value = Result.Idle
    }
}
