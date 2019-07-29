package com.example.di

import com.example.ui.main.MainActivity
import com.example.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class,
            NavHostModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}