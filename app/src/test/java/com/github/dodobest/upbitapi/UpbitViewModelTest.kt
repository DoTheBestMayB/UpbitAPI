package com.github.dodobest.upbitapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.dodobest.data.Injector
import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.upbitapi.data.UpbitFakeRemoteDataSet
import com.github.dodobest.upbitapi.data.UpbitFakeRemoteDataSource
import com.github.dodobest.upbitapi.scheduler.SchedulerProvider
import com.github.dodobest.upbitapi.scheduler.TrampolineSchedulerProvider
import com.github.dodobest.upbitapi.util.assertLiveData
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.IllegalArgumentException


class UpbitViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var upbitFakeRemoteDataSource: UpbitFakeRemoteDataSource
    private lateinit var upbitRepository: UpbitRepository
    private lateinit var getMarketsUseCase: GetMarketsUseCase
    private lateinit var getTickerUseCase: GetTickerUseCase
    private lateinit var schedulerProvider: SchedulerProvider
    private lateinit var upbitViewModel: UpbitViewModel

    @Before
    fun setUp() {
        upbitFakeRemoteDataSource = UpbitFakeRemoteDataSource()
        upbitRepository = Injector.provideUpbitRepository(upbitFakeRemoteDataSource)
        getMarketsUseCase = Injector.provideGetMarketsUseCase(upbitRepository)
        getTickerUseCase = Injector.provideGetTickerUseCase(upbitRepository)
        schedulerProvider = TrampolineSchedulerProvider()
        upbitViewModel = UpbitViewModel(getMarketsUseCase, getTickerUseCase, schedulerProvider)
    }

    @Test
    fun `getMarket을 호출하면 코인 데이터를 수신한다`() {
        // when
        upbitViewModel.getMarkets()

        // then
        try {
            val actual = upbitViewModel.marketData
            val expected = UpbitFakeRemoteDataSet.upbitMarketData.map { it.toDomainData() }
            assertLiveData(actual).isEqualTo(expected)
        } catch (exception: IllegalArgumentException) {
            println(exception)
        }
    }

    @Test
    fun `getMarkets를 호출한 뒤, 수신 받은 코인에 대해 getTicker를 호출하면 KRW-BTC Ticker 데이터를 수신한다`() {
        // given
        upbitViewModel.getMarkets()

        // when
        upbitViewModel.getTicker(BTC_COIN_NAME)

        // then
        try {
            val actual = upbitViewModel.tickerData
            val expected = mutableMapOf(BTC_COIN_NAME to UpbitFakeRemoteDataSet.upbitBTCTickerData[0].toDomainData())
            assertLiveData(actual).isEqualTo(expected)
        } catch (exception: IllegalArgumentException) {
            println(exception)
        }
    }

    @Test
    fun `getMarkets를 호출한 뒤, 수신 받지 않은 코인에 대해 getTicker를 호출하면 빈 데이터를 수신한다`() {
        // given
        upbitViewModel.getMarkets()

        // when
        upbitViewModel.getTicker(NO_EXIST_COIN_NAME)

        // then
        try {
            val actual = upbitViewModel.tickerData
            val expected = null
            assertLiveData(actual).isEqualTo(expected)
        } catch (exception: IllegalArgumentException) {
            println(exception)
        }
    }

    companion object {
        private const val BTC_COIN_NAME = "KRW-BTC"
        private const val NO_EXIST_COIN_NAME = "KWR-BTW"
    }
}