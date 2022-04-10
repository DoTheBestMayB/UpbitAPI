package com.github.dodobest.domain.factory

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetMarketsUseCaseImpl

object GetMarketsUseCaseFactory {
    private lateinit var getMarketsUseCase: GetMarketsUseCase

    fun getGetMarketsUseCase(upbitRepository: UpbitRepository): GetMarketsUseCase {
        if (::getMarketsUseCase.isInitialized) {
            return getMarketsUseCase
        }

        getMarketsUseCase = GetMarketsUseCaseImpl(upbitRepository)
        return getMarketsUseCase
    }
}