package com.github.dodobest.data.repository

import com.github.dodobest.data.data.UpbitRemoteDataSource
import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

internal class UpbitRepositoryImpl @Inject constructor(
    private val upbitRemoteDataSource: UpbitRemoteDataSource
) : UpbitRepository {
    override fun getMarkets(): Single<List<UpbitMarketData>> {
        return upbitRemoteDataSource.getMarkets().map { upbitMarketDataList ->
            upbitMarketDataList.map { upbitMarketData ->
                upbitMarketData.toDomainData()
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun getTicker(market: String): Single<List<UpbitTickerData>> {
        return upbitRemoteDataSource.getTicker(market).map { upbitTickerDataList ->
            upbitTickerDataList.map { upbitTickerData ->
                upbitTickerData.toDomainData()
            }
        }.subscribeOn(Schedulers.io())
    }
}