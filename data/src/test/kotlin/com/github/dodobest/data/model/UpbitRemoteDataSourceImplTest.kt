package com.github.dodobest.data.model

import com.github.dodobest.data.remote.UpbitAPI
import com.github.dodobest.data.remote.UpbitRemoteDataSource
import com.github.dodobest.data.remote.UpbitRemoteDataSourceImpl
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

internal class UpbitRemoteDataSourceImplTest {
    private lateinit var upbitAPI: UpbitAPI
    private lateinit var upbitRemoteDataSource: UpbitRemoteDataSource

    @Before
    fun setUp() {
        upbitAPI = mockk(relaxed=true)
        upbitRemoteDataSource = UpbitRemoteDataSourceImpl(upbitAPI)
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