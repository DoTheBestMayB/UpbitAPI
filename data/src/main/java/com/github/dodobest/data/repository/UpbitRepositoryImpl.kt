package com.github.dodobest.data.repository

import com.github.dodobest.data.factory.SingletonHolder
import com.github.dodobest.data.data.UpbitRemoteDataSource
import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single

internal class UpbitRepositoryImpl(
    private val upbitRemoteDataSource: UpbitRemoteDataSource
) : UpbitRepository {
    override fun getMarkets(): Single<List<UpbitMarketData>> {
        return upbitRemoteDataSource.getMarkets().map { upbitMarketDataList ->
            upbitMarketDataList.map { upbitMarketData ->
                upbitMarketData.toDomainData()
            }
        }
    }

    override fun getTicker(market: String): Single<List<UpbitTickerData>> {
        return upbitRemoteDataSource.getTicker(market).map { upbitTickerDataList ->
            upbitTickerDataList.map { upbitTickerData ->
                upbitTickerData.toDomainData()
            }
        }
    }

    companion object :
        SingletonHolder<UpbitRepository, UpbitRemoteDataSource>(::UpbitRepositoryImpl)
}