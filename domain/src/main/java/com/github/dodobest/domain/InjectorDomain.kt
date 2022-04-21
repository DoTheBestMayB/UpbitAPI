package com.github.dodobest.domain

import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetMarketsUseCaseImpl
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCaseImpl

object InjectorDomain {
    fun provideGetMarketsUseCase(upbitRepository: UpbitRepository): GetMarketsUseCase {
        return GetMarketsUseCaseImpl.getInstance(upbitRepository)
    }

    fun provideGetTickerUseCase(upbitRepository: UpbitRepository): GetTickerUseCase {
        return GetTickerUseCaseImpl.getInstance(upbitRepository)
    }
}