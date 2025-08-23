package com.example.stylish.DomainLayer.util

sealed class Result<out T>{
    data object Idle: Result<Nothing>()
    data object Loading: Result<Nothing>()
    data class Success<out T>(val data:T): Result<T>()
    data class Failure(val message: String) : Result<Nothing>()
}