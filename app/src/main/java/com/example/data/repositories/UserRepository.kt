package com.example.data.repositories

import androidx.lifecycle.LiveData
import com.example.data.entities.User
import com.example.network.Resource
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

interface UserRepository {

    fun getUsers(
        coroutineScope: CoroutineScope,
        coroutineContext: CoroutineContext
    ): LiveData<Resource<List<User>>>

}