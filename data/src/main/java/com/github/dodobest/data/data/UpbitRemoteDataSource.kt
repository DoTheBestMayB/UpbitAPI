package com.github.dodobest.data.data

import com.github.dodobest.data.model.UpbitMarketData
import com.github.dodobest.data.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single

interface UpbitRemoteDataSource {
    fun getMarkets(): Single<List<UpbitMarketData>>
    fun getTicker(coinName: String): Single<List<UpbitTickerData>>
}