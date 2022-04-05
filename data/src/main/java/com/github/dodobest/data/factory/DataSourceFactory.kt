package com.github.dodobest.data.factory

import com.github.dodobest.data.remote.UpbitAPI
import com.github.dodobest.data.remote.UpbitRemoteDataSourceImpl

internal interface DataSourceFactory {
    companion object {
        fun newUpbitDataSource() : UpbitRemoteDataSourceImpl {
            return UpbitRemoteDataSourceImpl(RetrofitClient.getUpbitRetrofit().create(UpbitAPI::class.java))
        }
    }
}