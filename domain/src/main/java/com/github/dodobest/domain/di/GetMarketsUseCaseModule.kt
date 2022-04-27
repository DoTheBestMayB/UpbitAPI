package com.github.dodobest.domain.di

import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetMarketsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class GetMarketsUseCaseModule {
    @Binds
    internal abstract fun bindGetMarketsUseCase(
        getMarketsUseCaseImpl: GetMarketsUseCaseImpl
    ): GetMarketsUseCase
}