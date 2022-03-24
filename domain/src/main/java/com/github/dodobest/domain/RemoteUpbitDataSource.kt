package com.github.dodobest.domain

interface RemoteUpbitDataSource {
    fun getMarkets(onSuccess: (List<UpbitMarketData>) -> Unit, onFailure: (Throwable) -> Unit)

    fun getTicker(coinName: String, onSuccess: (List<UpbitTickerData>) -> Unit, onFailure: (Throwable) -> Unit)
}