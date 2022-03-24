package com.github.dodobest.data

import com.github.dodobest.domain.RemoteUpbitDataSource
import com.github.dodobest.domain.UpbitMarketData
import com.github.dodobest.domain.UpbitTickerData
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit

internal class RemoteUpbitDataSourceImpl(
    private val retrofit: Retrofit
): RemoteUpbitDataSource {
    override fun getMarkets(
        onSuccess: (List<UpbitMarketData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val singleMarketData = retrofit.create(UpbitApiService::class.java).getMarkets()

        singleMarketData.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe({
                onSuccess(it.map { it.toData() })
            }, {
                onFailure(it)
            })
    }

    override fun getTicker(coinName: String, onSuccess: (List<UpbitTickerData>) -> Unit, onFailure: (Throwable) -> Unit) {
        val singleTickerData = retrofit.create(UpbitApiService::class.java).getTicker(coinName)

        singleTickerData.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe({
                onSuccess(it.map { it.toData() })
            }, {
                onFailure(it)
            })
    }

}