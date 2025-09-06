//package com.example.stylish.DomainLayer.usecase
//
//import com.example.stylish.DomainLayer.repository.AuthRepository
//import com.example.stylish.DomainLayer.util.Result
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//
//class GoogleSignInUseCase (private val repository: AuthRepository){
//    suspend operator fun invoke (account: GoogleSignInAccount): Result<String> {
//        return repository.signInWithGoogle(account)
//    }
//}