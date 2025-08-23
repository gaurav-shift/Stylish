package com.example.stylish.DomainLayer.repository

import com.example.stylish.DomainLayer.util.Result
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface AuthRepository {
    suspend fun login(email: String,password: String) : Result<String>
    suspend fun signup(email: String,password: String) : Result<String>
    suspend fun signInWithGoogle(account: GoogleSignInAccount) : Result<String>
}