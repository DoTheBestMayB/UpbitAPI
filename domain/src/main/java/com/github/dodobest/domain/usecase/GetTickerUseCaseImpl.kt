package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetTickerUseCaseImpl @Inject constructor(
    private val upbitRepository: UpbitRepository
) : GetTickerUseCase {
    override fun execute(market: String): Single<List<UpbitTickerData>> {
        return upbitRepository.getTicker(market)
    }
}