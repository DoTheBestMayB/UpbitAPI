package com.github.dodobest.data.repository

import com.github.dodobest.data.data.UpbitRemoteDataSource
import com.github.dodobest.data.hilt.UpbitDataModule
import com.github.dodobest.domain.UpbitRepository
import io.mockk.mockk
import io.mockk.verify

import org.junit.Before
import org.junit.Test

internal class UpbitRepositoryImplTest {
    private lateinit var upbitRemoteDataSource: UpbitRemoteDataSource
    private lateinit var upbitRepository: UpbitRepository

    @Before
    fun setUp() {
        upbitRemoteDataSource = mockk(relaxed = true)
        upbitRepository = UpbitDataModule.provideUpbitRepository(upbitRemoteDataSource)
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
        upbitRepository.getTicker(COIN_NAME)

        // then
        verify { upbitRemoteDataSource.getTicker(COIN_NAME) }
    }

    companion object {
        private const val COIN_NAME = "KRW-BTC"
    }
}