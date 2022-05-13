package com.github.dodobest.data.di

import com.github.dodobest.data.data.UpbitRemoteDataSource
import com.github.dodobest.data.data.UpbitRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataSourceModule {

    @Binds
    fun bindUpbitRemoteDataSource(
        upbitRemoteDataSourceImpl: UpbitRemoteDataSourceImpl
    ): UpbitRemoteDataSource
}