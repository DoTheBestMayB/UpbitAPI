package com.github.dodobest.data.repository

import com.github.dodobest.data.factory.SingletonHolder
import com.github.dodobest.data.remote.UpbitRemoteDataSource
import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single

internal class UpbitRepositoryImpl(
    private val upbitRemoteDataSource: UpbitRemoteDataSource
) : UpbitRepository {
    override fun getMarkets(): Single<List<UpbitMarketData>> {
        return upbitRemoteDataSource.getMarkets().map { upbitMarketDataList
            ->
            upbitMarketDataList.map { upbitMarketData
                ->
                upbitMarketData.toDomainData()
            }
        }
    }

    override fun getTicker(coinName: String): Single<List<UpbitTickerData>> {
        return upbitRemoteDataSource.getTicker(coinName).map { upbitTickerDataList
            ->
            upbitTickerDataList.map { upbitTickerData
                ->
                upbitTickerData.toDomainData()
            }
        }
    }

    companion object : SingletonHolder<UpbitRepository, UpbitRemoteDataSource>(::UpbitRepositoryImpl)
}