package com.github.dodobest.data.di

import com.github.dodobest.data.repository.UpbitRepositoryImpl
import com.github.dodobest.domain.UpbitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UpbitRepositoryModule {
    @Binds
    fun provideUpbitRepository(
        upbitRepositoryImpl: UpbitRepositoryImpl
    ): UpbitRepository
}