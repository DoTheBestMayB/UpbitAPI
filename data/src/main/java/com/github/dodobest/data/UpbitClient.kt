package com.github.dodobest.data

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class UpbitClient {
    fun getAPI(): UpbitAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build())
            .build()
            .create(UpbitAPI::class.java)
    }

    companion object {
        const val BASE_URL : String = "https://api.upbit.com/v1/"
        const val CONNECT_TIMEOUT: Long = 3L
        const val READ_TIMEOUT: Long = 3L
        const val WRITE_TIMEOUT: Long = 3L
    }
}