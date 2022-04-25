package com.github.dodobest.data.hilt

import com.github.dodobest.data.repository.UpbitRepositoryImpl
import com.github.dodobest.domain.UpbitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UpbitRepositoryModule {

    @Binds
    internal abstract fun bindUpbitRepository(
        upbitRepositoryImpl: UpbitRepositoryImpl
    ) : UpbitRepository
}