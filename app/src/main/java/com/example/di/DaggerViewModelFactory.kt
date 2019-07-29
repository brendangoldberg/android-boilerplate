package com.example.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class DaggerViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(p0: Class<T>): T {
        val clazz = creators[p0] ?: throw IllegalArgumentException("unknown ViewModel class $p0")

        try {
            @Suppress("unchecked_cast")
            return clazz.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}