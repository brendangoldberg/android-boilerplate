package com.example.di

import com.example.ui.shared.DaggerNavHostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class NavHostModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeNavHostFragment(): DaggerNavHostFragment

}