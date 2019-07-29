package com.example.data.repositories

import com.example.data.services.UserService
import com.example.network.ApiClient
import javax.inject.Inject

class DefaultServiceRepository @Inject constructor(
    private val client: ApiClient
) : ServiceRepository {

    override fun userService(): UserService = client.create()
}