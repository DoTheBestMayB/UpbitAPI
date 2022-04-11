package com.github.dodobest.data.factory

import com.github.dodobest.data.remote.UpbitAPI
import com.github.dodobest.data.remote.UpbitRemoteDataSource
import com.github.dodobest.data.remote.UpbitRemoteDataSourceImpl
import retrofit2.Retrofit

internal interface DataSourceFactory {
    companion object {
        fun newUpbitDataSource(retrofit: Retrofit): UpbitRemoteDataSource {
            return UpbitRemoteDataSourceImpl(retrofit.create(UpbitAPI::class.java))
        }
    }
}