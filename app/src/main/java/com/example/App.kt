package com.example

import com.example.di.DaggerAppComponent
import com.example.testing.OpenForTesting
import com.microsoft.appcenter.AppCenter
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

@OpenForTesting
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AppCenter.start(this, BuildConfig.APPCENTER_SECRET)
    }

}