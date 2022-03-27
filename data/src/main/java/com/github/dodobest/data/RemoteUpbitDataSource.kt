package com.github.dodobest.data

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class RemoteUpbitDataSource(
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(API.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder()
            .connectTimeout(API.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(API.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(API.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build())
        .build()
) {
    fun getMarkets(
        onSuccess: (List<UpbitMarketData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val singleMarketData = retrofit.create(UpbitApiService::class.java).getMarkets()

        singleMarketData.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe({
                onSuccess(it)
            }, {
                onFailure(it)
            })
    }

    fun getTicker(coinName: String, onSuccess: (List<UpbitTickerData>) -> Unit, onFailure: (Throwable) -> Unit) {
        val singleTickerData = retrofit.create(UpbitApiService::class.java).getTicker(coinName)

        singleTickerData.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe({
                onSuccess(it)
            }, {
                onFailure(it)
            })
    }

}