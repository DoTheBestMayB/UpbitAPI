package com.github.dodobest.data.remote

import com.github.dodobest.data.model.UpbitMarketData
import com.github.dodobest.data.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single

internal class UpbitRemoteDataSourceImpl(
    private val upbitAPI: UpbitAPI
) : UpbitRemoteDataSource {
    override fun getMarkets(): Single<List<UpbitMarketData>> {
        return upbitAPI.getMarkets()
    }

    override fun getTicker(coinName: String): Single<List<UpbitTickerData>> {
        return upbitAPI.getTicker(coinName)
    }

}