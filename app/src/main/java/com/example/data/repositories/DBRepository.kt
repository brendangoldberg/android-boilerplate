package com.example.data.repositories

import com.example.data.daos.UserDao

interface DBRepository {

    fun userDao(): UserDao

}