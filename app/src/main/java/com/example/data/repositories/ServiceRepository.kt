package com.example.data.repositories

import com.example.data.services.UserService

interface ServiceRepository {

    fun userService(): UserService

}