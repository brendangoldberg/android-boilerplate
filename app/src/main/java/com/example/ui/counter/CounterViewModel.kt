package com.example.ui.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CounterViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val DEFAULT_COUNT = 10
    }

    private val _count = MutableLiveData<Int>().apply { postValue(DEFAULT_COUNT) }
    val count: LiveData<Int> get() = _count

    fun increaseCount() {
        val incremented = (_count.value ?: DEFAULT_COUNT).inc()

        _count.postValue(incremented)
    }

    fun decreaseCount() {
        val decremented = (_count.value ?: DEFAULT_COUNT).dec()

        _count.postValue(decremented)
    }

}