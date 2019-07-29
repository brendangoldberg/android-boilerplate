package com.example.data.repositories

import com.example.data.services.UserService
import org.mockito.Mockito.mock

class FakeServiceRepository : ServiceRepository {

    private val fakeUserService = mock(UserService::class.java)

    override fun userService(): UserService = fakeUserService

}