package com.example.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.entities.User
import com.example.network.NetworkResource
import com.example.network.NetworkResourceHandler
import com.example.network.Resource
import com.example.network.request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DefaultUserRepository @Inject constructor(
    dbRepository: DBRepository,
    serviceRepository: ServiceRepository
) : UserRepository {

    private val userService = serviceRepository.userService()
    private val userDao = dbRepository.userDao()

    override fun getUsers(
        coroutineScope: CoroutineScope,
        coroutineContext: CoroutineContext
    ): LiveData<Resource<List<User>>> {
        return object : NetworkResource<List<User>, List<User>>(coroutineScope, coroutineContext) {

            override suspend fun serviceRequest(): Response<List<User>> = userService.getUsers()

            override suspend fun saveRequest(data: List<User>) {
                userDao.insert(data)
            }

            override fun loadFromDb(): LiveData<List<User>> = userDao.findAllLiveData()

            override fun shouldFetch(data: List<User>?): Boolean {
                return data == null || data.isEmpty()
            }

        }.result
    }

}