package com.github.dodobest.upbitapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.dodobest.data.Injector

class UpbitViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            UpbitViewModel::class.java -> createViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createViewModel(): UpbitViewModel {
        val upbitRetrofit = Injector.provideUpbitRetrofit()
        val upbitAPI = Injector.provideUpbitAPI(upbitRetrofit)
        val upbitRemoteDataSource = Injector.provideUpbitRemoteDataSource(upbitAPI)
        val upbitRepository = Injector.provideUpbitRepository(upbitRemoteDataSource)
        val getMarketsUseCase = Injector.provideGetMarketsUseCase(upbitRepository)
        val getTickerUseCase = Injector.provideGetTickerUseCase(upbitRepository)

        return UpbitViewModel(getMarketsUseCase, getTickerUseCase)
    }
}