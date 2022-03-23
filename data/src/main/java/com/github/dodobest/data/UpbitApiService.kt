package com.github.dodobest.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UpbitApiService {
    @GET(API.MARKET_COIN_LIST_URL)
    fun getMarkets(@Query("isDetails") isDetails: Boolean = true): Single<List<UpbitMarketDataResponse>>

    @GET(API.TICKER_URL)
    fun getTicker(@Query(value = "markets") coinName: String): Single<List<UpbitTickerDataResponse>>
}