package com.github.dodobest.data

import com.github.dodobest.domain.RemoteUpbitDataSource
import com.github.dodobest.domain.Repository
import com.github.dodobest.domain.UpbitMarketData
import com.github.dodobest.domain.UpbitTickerData


internal class DefaultRepository(
    private val remoteUpbitDataSource: RemoteUpbitDataSource
): Repository{
    override fun getMarkets(
        onSuccess: (List<UpbitMarketData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        remoteUpbitDataSource.getMarkets(onSuccess, onFailure)
    }

    override fun getTicker(
        coinName: String,
        onSuccess: (List<UpbitTickerData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        remoteUpbitDataSource.getTicker(coinName, onSuccess, onFailure)
    }
}