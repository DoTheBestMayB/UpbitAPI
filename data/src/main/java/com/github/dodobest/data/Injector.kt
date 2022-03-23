package com.github.dodobest.data

import com.github.dodobest.domain.UpbitRepository
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Injector {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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