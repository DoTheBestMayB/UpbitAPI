package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.model.UpbitMarketData
import io.reactivex.rxjava3.core.Single

interface GetMarketsUseCase {
    operator fun invoke(): Single<List<UpbitMarketData>>

    companion object {
        fun of(upbitRepository: UpbitRepository): GetMarketsUseCase {
            return GetMarketsUseCaseImpl(upbitRepository)
        }
    }
}