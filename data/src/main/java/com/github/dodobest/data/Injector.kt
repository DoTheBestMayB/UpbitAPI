package com.github.dodobest.data

import com.github.dodobest.domain.UpbitRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Injector {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .connectTimeout(API.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(API.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(API.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build())
            .build()
    }

    fun provideUpbitRepository(retrofit: Retrofit): UpbitRepository {
        return UpbitRepositoryImpl(retrofit)
    }
}