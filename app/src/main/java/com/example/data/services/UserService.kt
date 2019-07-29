package com.example.data.services

import com.example.data.entities.User
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}