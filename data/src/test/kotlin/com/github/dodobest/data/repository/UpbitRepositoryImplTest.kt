package com.github.dodobest.data.repository

import com.github.dodobest.data.remote.UpbitRemoteDataSource
import io.mockk.mockk
import io.mockk.verify

import org.junit.Before
import org.junit.Test

internal class UpbitRepositoryImplTest {
    lateinit var upbitRemoteDataSource: UpbitRemoteDataSource
    lateinit var upbitRepository: UpbitRepositoryImpl

    @Before
    fun setUp() {
        upbitRemoteDataSource = mockk(relaxed = true)
        upbitRepository = UpbitRepositoryImpl(upbitRemoteDataSource)
    }

    @Test
    fun `upbitRepository getMarkets()를 호출하면 upbitRemoteDataSource getMarkets()를 호출한다`() {
        // when
        upbitRepository.getMarkets()

        // then
        verify { upbitRemoteDataSource.getMarkets() }
    }

    @Test
    fun `upbitRepository getTicker()를 호출하면 upbitRemoteDataSource getTicker()를 호출한다`() {
        // when
        upbitRepository.getTicker("KRW-BTC")

        // then
        verify { upbitRemoteDataSource.getTicker("KRW-BTC") }
    }
}