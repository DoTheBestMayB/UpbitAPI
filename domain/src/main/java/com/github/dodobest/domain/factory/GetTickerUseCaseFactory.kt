package com.github.dodobest.domain.factory

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCaseImpl

object GetTickerUseCaseFactory {
    private lateinit var getTickerUseCase: GetTickerUseCase

    fun getGetTickerUseCase(upbitRepository: UpbitRepository): GetTickerUseCase {
        if (::getTickerUseCase.isInitialized) {
            return getTickerUseCase
        }

        getTickerUseCase = GetTickerUseCaseImpl(upbitRepository)

        return getTickerUseCase
    }
}