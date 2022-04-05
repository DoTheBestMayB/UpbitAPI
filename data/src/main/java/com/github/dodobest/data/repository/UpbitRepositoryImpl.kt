package com.github.dodobest.data.repository

import com.github.dodobest.data.remote.UpbitRemoteDataSource
import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import io.reactivex.rxjava3.schedulers.Schedulers

internal class UpbitRepositoryImpl(
    private val upbitRemoteDataSource: UpbitRemoteDataSource
) : UpbitRepository {
    override fun getMarkets(onSuccess: (List<UpbitMarketData>) -> Unit, onFailure: (Throwable) -> Unit) {
        val singleMarketData = upbitRemoteDataSource.getMarkets()

        singleMarketData.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe({
                onSuccess(it.map { it.toDomainData() })
            }, {
                onFailure(it)
            })
    }

    override fun getTicker(coinName: String, onSuccess: (List<UpbitTickerData>) -> Unit, onFailure: (Throwable) -> Unit) {
        val singleTickerData = upbitRemoteDataSource.getTicker(coinName)

        singleTickerData.subscribeOn(Schedulers.io())
            .subscribe({
                onSuccess(it.map { it.toDomainData() })
            }, {
                onFailure(it)
            })
    }
}