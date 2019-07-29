package com.example.di

import android.app.Application
import android.content.Context
import com.example.data.local.AppDatabase
import dagger.Module
import dagger.Provides

@Module
object AndroidModule {

    @Provides
    @JvmStatic
    fun provideContext(app: Application): Context = app

    @Provides
    @JvmStatic
    fun provideAppDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)

}