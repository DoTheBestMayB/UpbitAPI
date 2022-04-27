package com.github.dodobest.data.di

import com.github.dodobest.data.data.UpbitRemoteDataSource
import com.github.dodobest.data.repository.UpbitRepositoryImpl
import com.github.dodobest.domain.UpbitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object UpbitRepositoryModule {
    @Provides
    internal fun provideUpbitRepository(
        upbitRemoteDataSource: UpbitRemoteDataSource
    ): UpbitRepository {
        return UpbitRepositoryImpl(upbitRemoteDataSource)
    }
}