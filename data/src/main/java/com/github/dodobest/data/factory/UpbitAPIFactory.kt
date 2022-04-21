package com.github.dodobest.data.factory

import com.github.dodobest.data.data.UpbitAPI
import retrofit2.Retrofit

internal class UpbitAPIFactory {
    companion object : SingletonHolder<UpbitAPI, Retrofit>({
        it.create(UpbitAPI::class.java)
    })
}