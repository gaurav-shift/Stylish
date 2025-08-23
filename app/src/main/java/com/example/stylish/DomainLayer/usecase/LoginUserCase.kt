package com.example.stylish.DomainLayer.usecase

import com.example.stylish.DomainLayer.repository.AuthRepository
import com.example.stylish.DomainLayer.util.Result

class LoginUserCase(private val repository: AuthRepository) {
    suspend operator fun invoke (email: String,password:String): Result<String> {
        return repository.login(email,password)
    }
}