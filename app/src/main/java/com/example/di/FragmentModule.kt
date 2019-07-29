package com.example.di

import com.example.ui.counter.CounterModule
import com.example.ui.main.MainModule
import com.example.ui.user.UserModule
import dagger.Module

@Module(
    includes = [
        FragmentFactoryModule::class,
        MainModule::class,
        CounterModule::class,
        UserModule::class
    ]
)
abstract class FragmentModule