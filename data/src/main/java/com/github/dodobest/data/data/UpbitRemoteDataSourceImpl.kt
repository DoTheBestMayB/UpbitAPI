package com.github.dodobest.data.data

import com.github.dodobest.data.model.UpbitMarketData
import com.github.dodobest.data.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

internal class UpbitRemoteDataSourceImpl @Inject constructor(
    private val upbitAPI: UpbitAPI
) : UpbitRemoteDataSource {
    override fun getMarkets(): Single<List<UpbitMarketData>> {
        return upbitAPI.getMarkets()
    }

    override fun getTicker(market: String): Single<List<UpbitTickerData>> {
        return upbitAPI.getTicker(market)
    }

}