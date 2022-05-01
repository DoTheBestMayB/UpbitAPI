package com.github.dodobest.data.di

import com.github.dodobest.data.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UpbitRetrofitModule {
    private const val UPBIT_BASE_URL = "https://api.upbit.com/v1/"

    @Provides
    @Singleton
    fun provideUpbitRetrofit(
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UPBIT_BASE_URL)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(Constant.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Constant.READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(Constant.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .build()
            )
            .build()
    }
}