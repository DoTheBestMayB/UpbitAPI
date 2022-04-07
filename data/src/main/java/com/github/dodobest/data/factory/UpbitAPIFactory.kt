package com.github.dodobest.data.factory

import com.github.dodobest.data.remote.UpbitAPI
import retrofit2.Retrofit

object UpbitAPIFactory {
    private lateinit var upbitAPI: UpbitAPI

    internal fun getUpbitAPI(retrofit: Retrofit) : UpbitAPI {
        if (::upbitAPI.isInitialized) {
            return upbitAPI
        }

        upbitAPI = retrofit.create(UpbitAPI::class.java)

        return upbitAPI
    }
}