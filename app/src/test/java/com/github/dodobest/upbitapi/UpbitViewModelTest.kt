package com.github.dodobest.upbitapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.upbitapi.model.UpbitFakeRemoteDataSet
import com.github.dodobest.upbitapi.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UpbitViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var getMarketsUseCase: GetMarketsUseCase
    private lateinit var getTickerUseCase: GetTickerUseCase
    private lateinit var upbitViewModel: UpbitViewModel

    @Before
    fun setUp() {
        getMarketsUseCase = mockk()
        getTickerUseCase = mockk()
        upbitViewModel = UpbitViewModel(getMarketsUseCase, getTickerUseCase)
    }

    @Test
    fun `UpbitViewModel getMarkets를 호출하면 코인 데이터를 수신한다`() {
        // given
        every { getMarketsUseCase.execute() } returns Single.just(
            UpbitFakeRemoteDataSet.upbitMarketData.map { it.toDomainData() }
        )

        // when
        upbitViewModel.getMarkets()

        // then
        assertThat(upbitViewModel.marketCoinNames.getOrAwaitValue())
            .isEqualTo(
                UpbitFakeRemoteDataSet.upbitMarketData.map { it.toDomainData() }
            )
    }

    @Test
    fun `서버에 있는 코인에 대해 getTicker를 호출하면 그 코인의 Ticker 데이터를 수신한다`() {
        // given
        every { getTickerUseCase.execute(BTC_CODE_NAME) } returns Single.just(
            UpbitFakeRemoteDataSet.upbitBTCTickerData.map { it.toDomainData() }
        )

        // when
        upbitViewModel.getTicker(BTC_CODE_NAME)

        // then
        assertThat(upbitViewModel.tickers.getOrAwaitValue())
            .isEqualTo(
                mapOf(BTC_CODE_NAME to UpbitFakeRemoteDataSet.upbitBTCTickerData[0].toDomainData())
            )
    }

    companion object {
        private const val BTC_CODE_NAME = "KRW-BTC"
    }
}