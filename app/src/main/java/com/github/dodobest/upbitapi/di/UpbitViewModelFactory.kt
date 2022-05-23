package com.github.dodobest.upbitapi.di

import com.github.dodobest.upbitapi.UpbitViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface UpbitViewModelFactory {
    fun create(marketPlaceName: String): UpbitViewModel
}