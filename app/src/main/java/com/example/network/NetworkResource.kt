package com.example.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class NetworkResource<DTO, Entity>(
    private val coroutineScope: CoroutineScope,
    private val coroutineContext: CoroutineContext
) : NetworkResourceHandler<DTO, Entity> {

    private val _result = MediatorLiveData<Resource<Entity>>()

    val result: LiveData<Resource<Entity>> get() = _result

    init {
        _result.postValue(Resource.Loading())

        @Suppress("LeakingThis")
        val row = loadFromDb()

        _result.addSource(row) { entity ->
            _result.removeSource(row)

            if (shouldFetch(entity)) {
                coroutineScope.launch { load(row) }
            } else {
                _result.addSource(row) {
                    setValue(Resource.Success(it))
                }
            }
        }
    }

    private fun setValue(value: Resource<Entity>) {
        if (_result.value != value) {
            _result.value = value
        }
    }

    private suspend fun load(row: LiveData<Entity>) {
        val response = withContext(coroutineContext) { request { serviceRequest() } }

        _result.addSource(row) {
            setValue(Resource.Loading(it))
        }

        when (response) {

            is Resource.Success -> {
                _result.removeSource(row)
                if (response.data != null) {
                    val entity = toEntity(response.data)
                    saveRequest(entity)
                    _result.addSource(loadFromDb()) {
                        setValue(Resource.Success(it))
                    }
                }
            }

            is Resource.Error -> {
                _result.removeSource(row)
                _result.addSource(row) {
                    setValue(Resource.Error(response.error))
                }
            }
        }
    }
}