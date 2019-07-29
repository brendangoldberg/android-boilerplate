package com.example.network

import retrofit2.Response
import java.lang.Exception

suspend fun <T> request(block: suspend () -> Response<T>): Resource<T?> {
    return try {
        val response = block()

        return if (response.isSuccessful) {
            Resource.Success(response.body())
        } else {
            Resource.Error(Throwable(response.message()))
        }
    } catch (e: Exception) {
        Resource.Error(e)
    }
}