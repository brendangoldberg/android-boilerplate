package com.example.di

import com.example.data.repositories.DefaultNameRepository
import com.example.data.repositories.NameRepository
import dagger.Module
import dagger.Provides

@Module(includes = [SharedRepositoryModule::class])
object RepositoryModule {

    @Provides
    @JvmStatic
    fun provideDefaultNameRepository() = DefaultNameRepository()

    @Provides
    @JvmStatic
    fun provideNameRepository(repository: DefaultNameRepository): NameRepository = repository

}