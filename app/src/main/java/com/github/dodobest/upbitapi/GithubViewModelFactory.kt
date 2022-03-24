package com.github.dodobest.upbitapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.dodobest.data.Injector

class GithubViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            UpbitApiViewModel::class.java -> createUpbitApiViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createUpbitApiViewModel(): UpbitApiViewModel {
        val retrofit = Injector.provideRetrofit()
        val remoteUpbitDataSource = Injector.provideUpbitDataSource(retrofit)
        val repository = Injector.provideDefaultRepository(remoteUpbitDataSource)
        return UpbitApiViewModel(repository)
    }
}