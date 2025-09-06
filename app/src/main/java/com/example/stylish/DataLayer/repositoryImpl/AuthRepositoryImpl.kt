package com.example.stylish.DataLayer.repositoryImpl

import android.util.Log
import com.example.stylish.DomainLayer.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import com.example.stylish.DomainLayer.util.Result

class AuthRepositoryImpl(
    private val  firebaseAuth: FirebaseAuth
): AuthRepository{
    override suspend fun login(
        email: String,
        password: String
    ): Result<String> {
       return try {
           firebaseAuth.signInWithEmailAndPassword(email,password).await()
           Result.Success("Login Successful")
       } catch (e: Exception){
           Result.Failure(e.localizedMessage ?: "Unknown Error During Login")
       }


    }

    override suspend fun signup(
        email: String,
        password: String
    ): Result<String> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email,password).await()

            // Sign out the user immediately after account creation
            // User should manually log in after registration
            firebaseAuth.signOut()
            Log.d("AuthRepositoryImpl", "User signed out after signup, current user: ${firebaseAuth.currentUser}")

            Result.Success("SignUp Successful")
        } catch (e: Exception){
            Result.Failure(e.localizedMessage ?: "Unknown Error During signUp")
        }
    }

//    override suspend fun signInWithGoogle(account: GoogleSignInAccount): Result<String> {
//        TODO("Not yet implemented")
//    }

}