package com.github.dodobest.data

import com.github.dodobest.domain.UpbitMarketData
import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.UpbitTickerData
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit

internal class UpbitRepositoryImpl(
    private val retrofit: Retrofit
): UpbitRepository {
    override fun getMarkets(
        onSuccess: (List<UpbitMarketData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val singleMarketData = retrofit.create(UpbitApiService::class.java).getMarkets()

        singleMarketData.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .doOnSuccess { onSuccess(it.map { it.toData() }) }
            .doOnError { onFailure(it) }
    }

    override fun getTicker(coinName: String, onSuccess: (List<UpbitTickerData>) -> Unit, onFailure: (Throwable) -> Unit) {
        val singleTickerData = retrofit.create(UpbitApiService::class.java).getTicker(coinName)

        singleTickerData.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .doOnSuccess { onSuccess(it.toData()) }
            .doOnError { onFailure(it) }
    }

}