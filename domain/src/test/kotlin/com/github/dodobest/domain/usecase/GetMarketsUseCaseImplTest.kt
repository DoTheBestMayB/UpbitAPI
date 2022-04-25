package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.hilt.UpbitDomainModule
import io.mockk.mockk
import io.mockk.verify

import org.junit.Before
import org.junit.Test

class GetMarketsUseCaseImplTest {
    lateinit var upbitRepository: UpbitRepository
    lateinit var getMarketsUseCase: GetMarketsUseCase

    @Before
    fun setUp() {
        upbitRepository = mockk(relaxed = true)
        getMarketsUseCase = UpbitDomainModule.provideGetMarketsUseCase(upbitRepository)
    }

    @Test
    fun `getMarketUseCase()를 호출하면 upbitRepository의 getMarkets()를 호출한다`() {
        // when
        getMarketsUseCase.execute()

        // then
        verify { upbitRepository.getMarkets() }
    }
}