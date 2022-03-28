package com.github.dodobest.data

import io.reactivex.rxjava3.core.Single

internal class UpbitRemoteDataSourceImpl(
    private val upbitAPI: UpbitAPI
) : UpbitRemoteDataSource{
    override fun getMarkets(): Single<List<UpbitMarketData>> {
        return upbitAPI.getMarkets()
    }

    override fun getTicker(coinName: String): Single<List<UpbitTickerData>> {
        return upbitAPI.getTicker(coinName)
    }

}