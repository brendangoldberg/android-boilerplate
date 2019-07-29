package com.example.di

import dagger.Module

@Module(
    includes = [
        AndroidModule::class,
        ActivityModule::class,
        RepositoryModule::class,
        NetworkModule::class
    ]
)
object AppModule
