package com.github.dodobest.upbitapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.dodobest.data.Injector
import com.github.dodobest.data.data.UpbitRemoteDataSource
import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UpbitViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var upbitRemoteDataSource: UpbitRemoteDataSource
    private lateinit var upbitRepository: UpbitRepository
    private lateinit var getMarketsUseCase: GetMarketsUseCase
    private lateinit var getTickerUseCase: GetTickerUseCase
    private lateinit var upbitViewModel: UpbitViewModel

    @Before
    fun setUp() {
        upbitRemoteDataSource = mockk(relaxed = true)
        upbitRepository = Injector.provideUpbitRepository(upbitRemoteDataSource)
        getMarketsUseCase = Injector.provideGetMarketsUseCase(upbitRepository)
        getTickerUseCase = Injector.provideGetTickerUseCase(upbitRepository)
        upbitViewModel = UpbitViewModel(getMarketsUseCase, getTickerUseCase)
    }

    @Test
    fun `UpbitViewModel getMarkets를 호출하면 UpbitRepository getMarkets를 호출한다`() {
        // when
        upbitViewModel.getMarkets()

        // then
        verify { upbitRepository.getMarkets() }
    }

    @Test
    fun `UpbitViewModel getTicker를 호출하면 UpbitRepository getTicker를 호출한다`() {
        // when
        upbitViewModel.getTicker(BTC_COIN_NAME)

        // then
        verify { upbitRepository.getTicker(BTC_COIN_NAME) }
    }

    companion object {
        private const val BTC_COIN_NAME = "KRW-BTC"
    }
}