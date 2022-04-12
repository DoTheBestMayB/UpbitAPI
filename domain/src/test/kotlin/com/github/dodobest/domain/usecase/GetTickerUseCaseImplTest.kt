package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.UpbitRepository
import io.mockk.mockk
import io.mockk.verify

import org.junit.Before
import org.junit.Test

class GetTickerUseCaseImplTest {
    lateinit var upbitRepository: UpbitRepository
    lateinit var getTickerUseCase: GetTickerUseCase

    @Before
    fun setUp() {
        upbitRepository = mockk(relaxed = true)
        getTickerUseCase = GetTickerUseCase.of(upbitRepository)
    }

    @Test
    fun `getTickerUseCase()를 호출하면 upbitRepository의 getTicker()를 호출한다`() {
        // when
        getTickerUseCase("KRW-BTC")

        // then
        verify { upbitRepository.getTicker("KRW-BTC") }
    }
}