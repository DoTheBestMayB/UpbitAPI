package com.github.dodobest.data

import com.github.dodobest.data.model.UpbitMarketData
import com.github.dodobest.data.model.UpbitTickerData
import com.github.dodobest.data.remote.UpbitAPI
import com.github.dodobest.data.remote.UpbitRemoteDataSource
import com.github.dodobest.data.remote.UpbitRemoteDataSourceImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

internal class UpbitRemoteDataSourceImplTest {
    private lateinit var upbitAPI: UpbitAPI
    private lateinit var upbitRemoteDataSource: UpbitRemoteDataSource
    private lateinit var upbitTickerDataMockk: Single<List<UpbitTickerData>>
    private lateinit var upbitMarketDataMockk: Single<List<UpbitMarketData>>

    @Before
    fun setUp() {
        upbitAPI = mockk(relaxed=true)
        upbitRemoteDataSource = UpbitRemoteDataSourceImpl(upbitAPI)
        upbitTickerDataMockk = mockk()
        upbitMarketDataMockk = mockk()
    }

    @Test
    fun `upbitRemoteDataSource getMarkets()를 호출하면 UpbitAPI getMarkets()를 호출한다`() {
        // when
        upbitRemoteDataSource.getMarkets()

        // then
        verify { upbitAPI.getMarkets() }
    }

    @Test
    fun `upbitRemoteDataSource getTicker()를 호출하면 UpbitAPI getTicker()를 호출한다`() {
        // when
        upbitRemoteDataSource.getTicker("KRW-BTC")

        // then
        verify { upbitAPI.getTicker("KRW-BTC") }
    }
}