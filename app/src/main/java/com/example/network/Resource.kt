package com.example.network

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val error: Throwable) : Resource<Nothing>()
    data class Loading<out T>(val cached: T? = null) : Resource<T>()
}