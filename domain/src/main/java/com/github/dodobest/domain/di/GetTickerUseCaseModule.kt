package com.github.dodobest.domain.di

import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class GetTickerUseCaseModule {
    @Binds
    internal abstract fun bindGetTickerUseCase(
        getTickerUseCaseImpl: GetTickerUseCaseImpl
    ): GetTickerUseCase
}