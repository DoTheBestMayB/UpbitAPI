package com.github.dodobest.data.repository

import com.github.dodobest.data.remote.UpbitRemoteDataSource
import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single

internal class UpbitRepositoryImpl(
    private val upbitRemoteDataSource: UpbitRemoteDataSource
) : UpbitRepository {
    override fun getMarkets(): Single<List<UpbitMarketData>> {
        return upbitRemoteDataSource.getMarkets().map { it ->
            it.map {
                it.toDomainData()
            }
        }
    }

    override fun getTicker(coinName: String): Single<List<UpbitTickerData>> {
        return upbitRemoteDataSource.getTicker(coinName).map { it ->
            it.map {
                it.toDomainData()
            }
        }
    }
}