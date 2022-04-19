package com.github.dodobest.upbitapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.dodobest.data.Injector
import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.upbitapi.data.UpbitFakeRemoteDataSet
import com.github.dodobest.upbitapi.data.UpbitFakeRemoteDataSource
import com.github.dodobest.upbitapi.util.assertLiveData
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UpbitViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var upbitFakeRemoteDataSource: UpbitFakeRemoteDataSource
    private lateinit var upbitRepository: UpbitRepository
    private lateinit var getMarketsUseCase: GetMarketsUseCase
    private lateinit var getTickerUseCase: GetTickerUseCase
    private lateinit var upbitViewModel: UpbitViewModel

    @Before
    fun setUp() {
        upbitFakeRemoteDataSource = UpbitFakeRemoteDataSource()
        upbitRepository = Injector.provideUpbitRepository(upbitFakeRemoteDataSource)
        getMarketsUseCase = Injector.provideGetMarketsUseCase(upbitRepository)
        getTickerUseCase = Injector.provideGetTickerUseCase(upbitRepository)
        upbitViewModel = UpbitViewModel(getMarketsUseCase, getTickerUseCase)
    }

    @Test
    fun `getMarkets를 호출하면 코인 데이터를 수신한다`() {
        // given
        val expected = UpbitFakeRemoteDataSet.upbitMarketData.map { it.toDomainData() }

        // when
        upbitViewModel.getMarkets()

        // then
        val actual = upbitViewModel.marketData
        assertLiveData(actual).isEqualTo(expected)
    }

    @Test
    fun `서버에 있는 코인에 대해 getTicker를 호출하면 그 코인의 Ticker 데이터를 수신한다`() {
        // given
        val expected = mutableMapOf(BTC_COIN_NAME to UpbitFakeRemoteDataSet.upbitBTCTickerData[0].toDomainData())

        // when
        upbitViewModel.getTicker(BTC_COIN_NAME)

        // then
        val actual = upbitViewModel.tickerData
        assertLiveData(actual).isEqualTo(expected)

    }

    @Test
    fun `서버에 없는 코인에 대해 getTicker를 호출하면 빈 데이터를 수신한다`() {
        // given
        val expected = null

        // when
        upbitViewModel.getTicker(NO_EXIST_COIN_NAME)

        // then
        val actual = upbitViewModel.tickerData
        assertLiveData(actual).isEqualTo(expected)

    }

    companion object {
        private const val BTC_COIN_NAME = "KRW-BTC"
        private const val NO_EXIST_COIN_NAME = "KWR-BTW"
    }
}