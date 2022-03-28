package com.github.dodobest.data

import io.reactivex.rxjava3.core.Single

interface UpbitRemoteDataSource {
    fun getMarkets(): Single<List<UpbitMarketData>>
    fun getTicker(coinName: String): Single<List<UpbitTickerData>>
}