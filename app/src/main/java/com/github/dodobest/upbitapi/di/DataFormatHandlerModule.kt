package com.github.dodobest.upbitapi.di

import com.github.dodobest.upbitapi.DataFormatHandler
import com.github.dodobest.upbitapi.DataFormatHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataFormatHandlerModule {
    @Binds
    fun bindDataFormatHandler(
        dataFormatHandlerImpl: DataFormatHandlerImpl
    ): DataFormatHandler
}