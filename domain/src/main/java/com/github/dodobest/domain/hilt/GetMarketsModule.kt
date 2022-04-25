package com.github.dodobest.domain.hilt

import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetMarketsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GetMarketsModule {

    @Binds
    abstract fun bindGetMarketsUseCase(
        getMarketsUseCaseImpl: GetMarketsUseCaseImpl
    ) : GetMarketsUseCase
}