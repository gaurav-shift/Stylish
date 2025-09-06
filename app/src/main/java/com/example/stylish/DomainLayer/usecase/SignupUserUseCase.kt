package com.example.stylish.DomainLayer.usecase

import com.example.stylish.DomainLayer.repository.AuthRepository
import com.example.stylish.DomainLayer.util.Result

class SignupUserUseCase (private val repository: AuthRepository){
    suspend operator fun invoke(email : String, password: String): Result<String> {

        // 👉 Rule 1: Email khali nahi hona chahiye
        if (email.isBlank()) {
            return Result.Failure(message = "Email cannot be empty")
        }
        // 👉 Rule 2: Password chhota nahi hona chahiye
        if (password.length < 6) {
            return Result.Failure("Password must be at least 6 characters")
        }
        // 👉 Rule 3 (optional): Email format basic check
        if (!email.contains("@") || !email.contains(".")) {
            return Result.Failure("Invalid email format")
        }
        // ✅ Sab sahi hai, ab repository call karo
         return repository.signup(email,password)
    }

}