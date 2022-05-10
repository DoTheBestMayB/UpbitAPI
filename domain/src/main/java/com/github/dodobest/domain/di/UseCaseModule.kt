package com.github.dodobest.domain.di

import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetMarketsUseCaseImpl
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {

    @Binds
    @Singleton
    fun provideGetMarketsUseCase(getMarketsUseCaseImpl: GetMarketsUseCaseImpl): GetMarketsUseCase

    @Binds
    @Singleton
    fun provideGetTickerUseCase(getTickerUseCaseImpl: GetTickerUseCaseImpl): GetTickerUseCase
}