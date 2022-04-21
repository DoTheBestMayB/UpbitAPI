package com.github.dodobest.upbitapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.dodobest.data.Injector
import com.github.dodobest.domain.InjectorDomain

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
        val getMarketsUseCase = InjectorDomain.provideGetMarketsUseCase(upbitRepository)
        val getTickerUseCase = InjectorDomain.provideGetTickerUseCase(upbitRepository)

        return UpbitViewModel(getMarketsUseCase, getTickerUseCase)
    }
}