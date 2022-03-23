package com.github.dodobest.data

object API {
    const val BASE_URL : String = "https://api.upbit.com/v1/"
    const val TICKER_URL : String = "ticker?markets="
    const val MARKET_COIN_LIST_URL : String = "market/all"

    // OkHttpClient
    const val CONNECT_TIMEOUT: Long = 3L
    const val READ_TIMEOUT: Long = 3L
    const val WRITE_TIMEOUT: Long = 3L
}