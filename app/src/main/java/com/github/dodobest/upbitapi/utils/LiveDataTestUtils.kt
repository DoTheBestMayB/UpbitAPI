package com.github.dodobest.upbitapi.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Subject
import com.google.common.truth.Truth.assertThat
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <T> assertLiveData(liveData: LiveData<T>): Subject {
    return assertThat(getValue(liveData))
}

@Throws(InterruptedException::class)
private fun <T> getValue(liveData: LiveData<T>): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(changedData: T?) {
            data = changedData
            latch.countDown()
            liveData.removeObserver(this)
        }
    }
    liveData.observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)
    liveData.removeObserver(observer)
    return data
}