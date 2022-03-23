package com.github.dodobest.upbitapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dodobest.domain.UpbitRepository

class UpbitApiViewModel(
    private val upbitRepository: UpbitRepository
): ViewModel() {
    private val _responseText = MutableLiveData<String>()
        val responseText: LiveData<String>
            get() = _responseText

    val defaultCoinName = "KRW-BTC"


    fun getTicker(coinName: String) {
        upbitRepository.getTicker(coinName, {
            _responseText.postValue(it.toString())
        }, {
            _responseText.postValue(it.toString())
        })
    }

    fun getMarkets() {
        upbitRepository.getMarkets({
            _responseText.postValue(it.toString())
        }, {
            _responseText.postValue(it.toString())
        })
    }
}