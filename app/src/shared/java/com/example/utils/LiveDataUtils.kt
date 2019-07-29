package com.example.utils

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
fun <T> LiveData<T>.value(): T? {
    var data: T? = null
    val latch = CountDownLatch(1)

    val observer = object : Observer<T> {
        override fun onChanged(t: T?) {
            data = t
            latch.countDown()
            removeObserver(this)
        }
    }

    observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)

    return data
}