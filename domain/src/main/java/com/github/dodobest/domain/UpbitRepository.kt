package com.github.dodobest.domain

interface UpbitRepository {
    fun getMarkets(onSuccess: (List<UpbitMarketData>) -> Unit, onFailure: (Throwable) -> Unit)

    fun getTicker(coinName: String, onSuccess: (UpbitTickerData) -> Unit, onFailure: (Throwable) -> Unit)
}