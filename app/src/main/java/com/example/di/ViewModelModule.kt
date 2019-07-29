package com.example.di

import com.example.ui.counter.CounterViewModelModule
import com.example.ui.main.MainViewModelModule
import com.example.ui.user.UserViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelFactoryModule::class,
        MainViewModelModule::class,
        CounterViewModelModule::class,
        UserViewModelModule::class
    ]
)
abstract class ViewModelModule