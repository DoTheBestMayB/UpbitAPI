package com.github.dodobest.data

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class RemoteUpbitDataSource(
    private val upbitApiService: UpbitApiService
) {
    fun getMarkets(
        onSuccess: (List<UpbitMarketData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val singleMarketData = upbitApiService.getMarkets()

        singleMarketData.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe({
                onSuccess(it)
            }, {
                onFailure(it)
            })
    }

    fun getTicker(coinName: String, onSuccess: (List<UpbitTickerData>) -> Unit, onFailure: (Throwable) -> Unit) {
        val singleTickerData = upbitApiService.getTicker(coinName)

        singleTickerData.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe({
                onSuccess(it)
            }, {
                onFailure(it)
            })
    }

}