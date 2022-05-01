package com.github.dodobest.data.di

import com.github.dodobest.data.data.UpbitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object APIModule {
    @Provides
    @Singleton
    fun provideUpbitAPI(retrofit: Retrofit): UpbitAPI {
        return retrofit.create(UpbitAPI::class.java)
    }
}