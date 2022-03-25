package com.github.dodobest.data

internal class DefaultRepository(
    private val remoteUpbitDataSource: RemoteUpbitDataSource
) {
    fun getMarkets(
        onSuccess: (List<UpbitMarketData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        remoteUpbitDataSource.getMarkets(onSuccess, onFailure)
    }

    fun getTicker(
        coinName: String,
        onSuccess: (List<UpbitTickerData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        remoteUpbitDataSource.getTicker(coinName, onSuccess, onFailure)
    }
}