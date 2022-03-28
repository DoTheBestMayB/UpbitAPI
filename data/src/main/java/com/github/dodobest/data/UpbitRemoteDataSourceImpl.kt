package com.github.dodobest.data

import io.reactivex.rxjava3.core.Single

internal class UpbitRemoteDataSourceImpl(
    private val upbitApiService: UpbitApiService
) : UpbitRemoteDataSource{
    override fun getMarkets(): Single<List<UpbitMarketData>> {
        return upbitApiService.getMarkets()
    }

    override fun getTicker(coinName: String): Single<List<UpbitTickerData>> {
        return upbitApiService.getTicker(coinName)
    }

}