package com.github.dodobest.data.factory

import com.github.dodobest.data.data.UpbitAPI
import com.github.dodobest.data.data.UpbitRemoteDataSource
import com.github.dodobest.data.data.UpbitRemoteDataSourceImpl

internal interface DataSourceFactory {
    companion object {
        fun newUpbitDataSource(upbitAPI: UpbitAPI): UpbitRemoteDataSource {
            return UpbitRemoteDataSourceImpl(upbitAPI)
        }
    }
}