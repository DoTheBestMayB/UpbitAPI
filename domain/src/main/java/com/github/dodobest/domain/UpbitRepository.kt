package com.github.dodobest.domain

import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData

interface UpbitRepository {
    fun getMarkets(onSuccess: (List<UpbitMarketData>) -> Unit, onFailure: (Throwable) -> Unit)
    fun getTicker(coinName: String, onSuccess: (List<UpbitTickerData>) -> Unit, onFailure: (Throwable) -> Unit)
}