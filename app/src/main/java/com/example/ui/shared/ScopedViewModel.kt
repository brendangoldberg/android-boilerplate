package com.example.ui.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import org.jetbrains.annotations.TestOnly
import kotlin.coroutines.CoroutineContext

abstract class ScopedViewModel : ViewModel() {

    private var _coroutineContext: CoroutineContext = Dispatchers.IO

    val coroutineContext get() = _coroutineContext

    @TestOnly
    fun setCoroutineContext(coroutineContext: CoroutineContext) {
        _coroutineContext = coroutineContext
    }
}