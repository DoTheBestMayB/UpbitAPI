package com.github.dodobest.data

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class RemoteUpbitDataSource(
    private val upbitApiService: UpbitApiService
) {
    fun getMarkets(): Single<List<UpbitMarketData>> {
        return upbitApiService.getMarkets()
    }

    fun getTicker(coinName: String): Single<List<UpbitTickerData>> {
        return upbitApiService.getTicker(coinName)
    }

}