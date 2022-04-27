package com.github.dodobest.data.di

import com.github.dodobest.data.Constant
import com.github.dodobest.data.data.UpbitAPI
import com.github.dodobest.data.data.UpbitRemoteDataSource
import com.github.dodobest.data.repository.UpbitRepositoryImpl
import com.github.dodobest.domain.UpbitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object UpbitDataModule {
    @Provides
    fun provideUpbitRepository(
        upbitRemoteDataSource: UpbitRemoteDataSource
    ): UpbitRepository {
        return UpbitRepositoryImpl(upbitRemoteDataSource)
    }

    @Provides
    internal fun provideUpbitAPI(
        retrofit: Retrofit
    ): UpbitAPI {
        return retrofit.create(UpbitAPI::class.java)
    }

    @Provides
    fun provideUpbitRetrofit(
        baseUrl: String = Constant.UPBIT_BASE_URL
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(Constant.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Constant.READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(Constant.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .build()
            )
            .build()
    }

    @Provides
    fun provideUpbitBaseUrl(): String {
        return Constant.UPBIT_BASE_URL
    }
}