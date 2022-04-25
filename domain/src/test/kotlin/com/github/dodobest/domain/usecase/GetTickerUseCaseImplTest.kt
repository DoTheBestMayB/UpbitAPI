package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.hilt.UpbitDomainModule
import io.mockk.mockk
import io.mockk.verify

import org.junit.Before
import org.junit.Test

class GetTickerUseCaseImplTest {
    lateinit var upbitRepository: UpbitRepository
    lateinit var getTickerUseCase: GetTickerUseCase

    @Before
    fun setUp() {
        GetTickerUseCaseImpl.resetInstanceOnlyForTest()

        upbitRepository = mockk(relaxed = true)
        getTickerUseCase = UpbitDomainModule.provideGetTickerUseCase(upbitRepository)
    }

    @Test
    fun `getTickerUseCase()를 호출하면 upbitRepository의 getTicker()를 호출한다`() {
        // when
        getTickerUseCase.execute("KRW-BTC")

        // then
        verify { upbitRepository.getTicker("KRW-BTC") }
    }
}