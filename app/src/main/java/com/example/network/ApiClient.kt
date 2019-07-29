package com.example.network

import retrofit2.Retrofit
import javax.inject.Inject

class ApiClient @Inject constructor(
    val retrofit: Retrofit
) {
    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}
