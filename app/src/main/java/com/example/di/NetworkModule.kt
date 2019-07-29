package com.example.di

import com.example.network.ApiClient
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object NetworkModule {

    private val moshi = Moshi.Builder().build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://jsonplaceholder.typicode.com")
        .build()

    @Provides
    @JvmStatic
    fun provideRetrofit(): Retrofit {
        return retrofit
    }

    @Provides
    @JvmStatic
    fun provideApiClient(retrofit: Retrofit): ApiClient = ApiClient(retrofit)
}