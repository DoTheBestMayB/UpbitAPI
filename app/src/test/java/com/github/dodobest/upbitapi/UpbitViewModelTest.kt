package com.github.dodobest.upbitapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.upbitapi.model.MarketPlaceName
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
    fun `getTicker를 호출하면 코인의 Ticker 데이터를 수신한다`() {
        // given
        every {
            getTickerUseCase.execute(TICKER_QUERY)
        } returns Single.just(UpbitFakeRemoteDataSet.upbitTickerData)

        val inputData = UpbitFakeRemoteDataSet.upbitMarketData.map { it.toDomainData() }
        upbitViewModel.extractCoinName(inputData)

        // when
        val marketPlaceName = MarketPlaceName.from(KRW_POSITION_VALUE)
        if (marketPlaceName != null) {
            upbitViewModel.getTicker(inputData, marketPlaceName)
        } else {
            throw IllegalArgumentException(NO_MARKET_PLACE_NAME)
        }

        // then
        assertThat(upbitViewModel.tickers.getOrAwaitValue()).isEqualTo(
            UpbitFakeRemoteDataSet.upbitTickerDataWithKoreanName
        )
    }

    companion object {
        private const val TICKER_QUERY = "KRW-BTC,KRW-ETH,KRW-NU"
        private const val KRW_POSITION_VALUE = 0
        private const val NO_MARKET_PLACE_NAME = "등록되지 않은 마켓입니다."
    }
}