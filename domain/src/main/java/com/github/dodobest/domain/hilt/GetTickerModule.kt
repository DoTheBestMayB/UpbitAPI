package com.github.dodobest.domain.hilt

import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GetTickerModule {

    @Binds
    abstract fun bindGetTickerUseCase(
        getTickerUseCaseImpl: GetTickerUseCaseImpl
    ): GetTickerUseCase
}