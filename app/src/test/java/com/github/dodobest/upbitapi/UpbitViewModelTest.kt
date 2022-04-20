package com.github.dodobest.upbitapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.dodobest.data.Injector
import com.github.dodobest.data.data.UpbitRemoteDataSource
import com.github.dodobest.domain.InjectorDomain
import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.upbitapi.model.UpbitFakeRemoteDataSet
import com.github.dodobest.upbitapi.util.getValue
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

    private lateinit var upbitRemoteDataSource: UpbitRemoteDataSource
    private lateinit var upbitRepository: UpbitRepository
    private lateinit var getMarketsUseCase: GetMarketsUseCase
    private lateinit var getTickerUseCase: GetTickerUseCase
    private lateinit var upbitViewModel: UpbitViewModel

    @Before
    fun setUp() {
        upbitRemoteDataSource = mockk()
        upbitRepository = Injector.provideUpbitRepository(upbitRemoteDataSource)
        getMarketsUseCase = InjectorDomain.provideGetMarketsUseCase(upbitRepository)
        getTickerUseCase = InjectorDomain.provideGetTickerUseCase(upbitRepository)
        upbitViewModel = UpbitViewModel(getMarketsUseCase, getTickerUseCase)
    }

    @Test
    fun `UpbitViewModel getMarkets를 호출하면 코인 데이터를 수신한다`() {
        // given
        every { upbitRemoteDataSource.getMarkets() } returns Single.just(
            UpbitFakeRemoteDataSet.upbitMarketData
        )
        every { upbitRemoteDataSource.getTicker(BTC_CODE_NAME) } returns Single.just(
            UpbitFakeRemoteDataSet.upbitBTCTickerData
        )
        every { upbitRemoteDataSource.getTicker(ETH_CODE_NAME) } returns Single.just(
            UpbitFakeRemoteDataSet.upbitETHTickerData
        )
        every { upbitRemoteDataSource.getTicker(NU_CODE_NAME) } returns Single.just(
            UpbitFakeRemoteDataSet.upbitNUTickerData
        )

        // when
        upbitViewModel.getMarkets()

        // then
        assertThat(getValue(upbitViewModel.marketCoinNames)).isEqualTo(
            UpbitFakeRemoteDataSet.upbitMarketData.map {
                it.toDomainData()
            }
        )
        assertThat(getValue(upbitViewModel.tickers)).isEqualTo(
            UpbitFakeRemoteDataSet.upbitTickerData
        )
    }

    @Test
    fun `서버에 있는 코인에 대해 getTicker를 호출하면 그 코인의 Ticker 데이터를 수신한다`() {
        // given
        every { upbitRemoteDataSource.getTicker(BTC_CODE_NAME) } returns Single.just(
            UpbitFakeRemoteDataSet.upbitBTCTickerData
        )

        // when
        upbitViewModel.getTicker(BTC_CODE_NAME)

        // then
        assertThat(getValue(upbitViewModel.tickers)).isEqualTo(
            mapOf(BTC_CODE_NAME to UpbitFakeRemoteDataSet.upbitBTCTickerData[0].toDomainData())
        )
    }

    companion object {
        private const val BTC_CODE_NAME = "KRW-BTC"
        private const val ETH_CODE_NAME = "KRW-ETH"
        private const val NU_CODE_NAME = "KRW-NU"
    }
}