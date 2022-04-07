package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single

internal class GetTickerUseCaseImpl(
    private val upbitRepository: UpbitRepository
) : GetTickerUseCase {
    override operator fun invoke(coinName: String): Single<List<UpbitTickerData>> {
        return upbitRepository.getTicker(coinName)
    }
}