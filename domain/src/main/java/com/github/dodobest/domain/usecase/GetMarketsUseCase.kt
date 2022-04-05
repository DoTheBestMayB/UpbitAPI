package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.model.UpbitMarketData

internal class GetMarketsUseCase(private val upbitRepository: UpbitRepository){
    operator fun invoke(onSuccess: (List<UpbitMarketData>) -> Unit, onFailure: (Throwable) -> Unit) {
        upbitRepository.getMarkets(onSuccess, onFailure)
    }
}