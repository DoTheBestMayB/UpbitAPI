package com.github.dodobest.data.factory

import com.github.dodobest.data.Constant
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private lateinit var upbitRetrofitClient: Retrofit
    private const val UPBIT_BASE_URL: String = "https://api.upbit.com/v1"

    fun getUpbitRetrofit(): Retrofit {
        if (::upbitRetrofitClient.isInitialized) {
            return upbitRetrofitClient
        }

        upbitRetrofitClient = Retrofit.Builder()
            .baseUrl(UPBIT_BASE_URL)
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
        return upbitRetrofitClient
    }

}