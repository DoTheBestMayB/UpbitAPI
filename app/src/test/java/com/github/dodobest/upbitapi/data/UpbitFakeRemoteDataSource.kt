package com.github.dodobest.upbitapi.data

import com.github.dodobest.data.data.UpbitRemoteDataSource
import com.github.dodobest.data.model.UpbitMarketData
import com.github.dodobest.data.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single

class UpbitFakeRemoteDataSource : UpbitRemoteDataSource {
    override fun getMarkets(): Single<List<UpbitMarketData>> {
        return Single.just(UpbitFakeRemoteDataSet.upbitMarketData)
    }

    override fun getTicker(market: String): Single<List<UpbitTickerData>> {
        return when (market) {
            "KRW-BTC" -> Single.just(UpbitFakeRemoteDataSet.upbitBTCTickerData)
            "KRW_ETH" -> Single.just(UpbitFakeRemoteDataSet.upbitETHTickerData)
            "KRW-NU" -> Single.just(UpbitFakeRemoteDataSet.upbitNUTickerData)
            else -> Single.just(UpbitFakeRemoteDataSet.upbitTickerFailData)
        }
    }
}