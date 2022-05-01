package com.github.dodobest.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object GsonConverterModule {
    @Provides
    @Singleton
    fun provideGsonConverter() : GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}