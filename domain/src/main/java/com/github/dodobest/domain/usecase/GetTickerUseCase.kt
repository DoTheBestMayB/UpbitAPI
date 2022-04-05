package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.model.UpbitTickerData

internal class GetTickerUseCase(private val upbitRepository: UpbitRepository) {
    operator fun invoke(coinName: String, onSuccess: (List<UpbitTickerData>) -> Unit, onFailure: (Throwable) -> Unit) {
        upbitRepository.getTicker(coinName, onSuccess, onFailure)
    }
}