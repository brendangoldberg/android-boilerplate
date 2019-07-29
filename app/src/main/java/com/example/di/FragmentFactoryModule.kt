package com.example.di

import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module

@Module
@Suppress("unused")
abstract class FragmentFactoryModule {

    @Binds
    abstract fun bindFragmentFactory(factory: DaggerFragmentFactory): FragmentFactory

}