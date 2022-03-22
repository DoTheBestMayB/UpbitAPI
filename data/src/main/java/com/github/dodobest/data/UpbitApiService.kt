package com.github.dodobest.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UpbitApiService {
    @GET(API.MARKET_COIN_LIST_URL)
    fun getMarkets(@Query("isDetails") isDetails: Boolean = true): Response<List<UpbitMarketResponse>>

    @GET("${API.TICKER_URL}/{coinName}")
    fun getTicker(@Path(value = "coinName", encoded = true) coinName: String) : Response<List<UpbitTickerResponse>>
}