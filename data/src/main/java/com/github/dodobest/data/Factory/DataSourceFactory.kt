package com.github.dodobest.data.Factory

import com.github.dodobest.data.remote.UpbitAPI
import com.github.dodobest.data.remote.UpbitRemoteDataSourceImpl

internal interface DataSourceFactory {
    companion object {
        fun newUpbitDataSource() : UpbitRemoteDataSourceImpl {
            return UpbitRemoteDataSourceImpl(RetrofitClient.getUpbitRetrofit().create(UpbitAPI::class.java))
        }
    }
}