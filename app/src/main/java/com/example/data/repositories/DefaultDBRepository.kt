package com.example.data.repositories

import com.example.data.daos.UserDao
import com.example.data.local.AppDatabase
import javax.inject.Inject

class DefaultDBRepository @Inject constructor(
    private val db: AppDatabase
) : DBRepository {

    override fun userDao() = db.userDao()

}