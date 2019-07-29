package com.example.di

import com.example.data.local.AppDatabase
import com.example.data.repositories.*
import com.example.network.ApiClient
import dagger.Module
import dagger.Provides

@Module
object SharedRepositoryModule {

    @Provides
    @JvmStatic
    fun provideServiceRepository(client: ApiClient): ServiceRepository =
        DefaultServiceRepository(client)

    @Provides
    @JvmStatic
    fun provideDBRepository(database: AppDatabase): DBRepository =
        DefaultDBRepository(database)

    @Provides
    @JvmStatic
    fun provideUserRepository(
        dbRepository: DBRepository,
        serviceRepository: ServiceRepository
    ): UserRepository = DefaultUserRepository(
        dbRepository, serviceRepository
    )

}