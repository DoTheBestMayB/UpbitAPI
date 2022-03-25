package com.github.dodobest.data

import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit

internal class RemoteUpbitDataSource(
    private val retrofit: Retrofit
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