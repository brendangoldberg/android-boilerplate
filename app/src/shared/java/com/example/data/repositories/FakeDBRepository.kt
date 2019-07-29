package com.example.data.repositories

import com.example.data.daos.UserDao
import org.mockito.Mockito.mock

class FakeDBRepository : DBRepository {

    private val fakeUserDao = mock(UserDao::class.java)

    override fun userDao(): UserDao {
        return fakeUserDao
    }

}