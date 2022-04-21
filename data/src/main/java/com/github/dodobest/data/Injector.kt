package com.github.dodobest.data

import com.github.dodobest.data.factory.DataSourceFactory
import com.github.dodobest.data.factory.RetrofitClient
import com.github.dodobest.data.factory.UpbitAPIFactory
import com.github.dodobest.data.data.UpbitAPI
import com.github.dodobest.data.data.UpbitRemoteDataSource
import com.github.dodobest.data.repository.UpbitRepositoryImpl
import com.github.dodobest.domain.UpbitRepository
import retrofit2.Retrofit

object Injector {
    fun provideUpbitRetrofit(): Retrofit {
        return RetrofitClient.upbitRetrofitClient
    }

    fun provideUpbitAPI(retrofit: Retrofit): UpbitAPI {
        return UpbitAPIFactory.getInstance(retrofit)
    }

    fun provideUpbitRemoteDataSource(upbitAPI: UpbitAPI): UpbitRemoteDataSource {
        return DataSourceFactory.newUpbitDataSource(upbitAPI)
    }

    fun provideUpbitRepository(upbitRemoteDataSource: UpbitRemoteDataSource): UpbitRepository {
        return UpbitRepositoryImpl.getInstance(upbitRemoteDataSource)
    }
}