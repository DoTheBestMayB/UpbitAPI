package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.SingletonHolder
import com.github.dodobest.domain.model.UpbitMarketData
import io.reactivex.rxjava3.core.Single

class GetMarketsUseCaseImpl(
    private val upbitRepository: UpbitRepository
) : GetMarketsUseCase {
    override fun execute(): Single<List<UpbitMarketData>> {
        return upbitRepository.getMarkets()
    }

    companion object : SingletonHolder<GetMarketsUseCase, UpbitRepository>(::GetMarketsUseCaseImpl)
}