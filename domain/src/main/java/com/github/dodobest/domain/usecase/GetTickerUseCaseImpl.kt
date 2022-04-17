package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.UpbitRepository
import com.github.dodobest.domain.SingletonHolder
import com.github.dodobest.domain.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single

class GetTickerUseCaseImpl(
    private val upbitRepository: UpbitRepository
) : GetTickerUseCase {
    override fun execute(market: String): Single<List<UpbitTickerData>> {
        return upbitRepository.getTicker(market)
    }

    companion object : SingletonHolder<GetTickerUseCase, UpbitRepository>(::GetTickerUseCaseImpl)
}