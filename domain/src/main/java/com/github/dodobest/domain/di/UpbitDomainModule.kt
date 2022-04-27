package com.github.dodobest.domain.di

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetMarketsUseCaseImpl
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object UpbitDomainModule {
    @Provides
    fun provideGetMarketsUseCase(
        upbitRepository: UpbitRepository
    ): GetMarketsUseCase {
        return GetMarketsUseCaseImpl(upbitRepository)
    }

    @Provides
    fun provideGetTickerUseCase(
        upbitRepository: UpbitRepository
    ): GetTickerUseCase {
        return GetTickerUseCaseImpl(upbitRepository)
    }
}