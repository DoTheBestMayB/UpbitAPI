package com.github.dodobest.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitAPI {
    @GET(MARKET_COIN_LIST_URL)
    fun getMarkets(@Query("isDetails") isDetails: Boolean = true): Single<List<UpbitMarketData>>

    @GET(TICKER_URL)
    fun getTicker(@Query(value = "markets") coinName: String): Single<List<UpbitTickerData>>

    companion object {
        const val MARKET_COIN_LIST_URL : String = "market/all"
        const val TICKER_URL : String = "ticker"
    }
}