package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.model.UpbitMarketData
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMarketsUseCaseImpl @Inject constructor(
    private val upbitRepository: UpbitRepository
) : GetMarketsUseCase {
    override fun execute(): Single<List<UpbitMarketData>> {
        return upbitRepository.getMarkets()
    }
}