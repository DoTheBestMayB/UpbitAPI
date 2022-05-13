package com.github.dodobest.data.data

import com.github.dodobest.data.model.UpbitMarketData
import com.github.dodobest.data.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UpbitAPI {
    
    @GET(MARKET_COIN_LIST_URL)
    fun getMarkets(@Query("isDetails") isDetails: Boolean = true): Single<List<UpbitMarketData>>

    @GET(TICKER_URL)
    fun getTicker(@Query(value = "markets") market: String): Single<List<UpbitTickerData>>

    companion object {
        private const val MARKET_COIN_LIST_URL = "market/all"
        private const val TICKER_URL = "ticker"
    }
}