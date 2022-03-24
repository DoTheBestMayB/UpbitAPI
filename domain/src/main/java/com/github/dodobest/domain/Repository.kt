package com.github.dodobest.domain

interface Repository {
    fun getMarkets(onSuccess: (List<UpbitMarketData>) -> Unit, onFailure: (Throwable) -> Unit)

    fun getTicker(coinName: String, onSuccess: (List<UpbitTickerData>) -> Unit, onFailure: (Throwable) -> Unit)
}