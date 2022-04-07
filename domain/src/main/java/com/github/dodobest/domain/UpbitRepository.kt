package com.github.dodobest.domain

import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single

interface UpbitRepository {
    fun getMarkets() : Single<List<UpbitMarketData>>
    fun getTicker(coinName: String) : Single<List<UpbitTickerData>>
}