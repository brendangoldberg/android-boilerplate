package com.example.network

import androidx.lifecycle.LiveData
import retrofit2.Response

interface NetworkResourceHandler<DTO, Entity> {
    suspend fun serviceRequest(): Response<DTO>

    fun loadFromDb(): LiveData<Entity>

    suspend fun saveRequest(data: Entity)

    fun shouldFetch(data: Entity?): Boolean

    @Suppress("unchecked_cast")
    fun toEntity(data: DTO): Entity {
        return data as Entity
    }
}