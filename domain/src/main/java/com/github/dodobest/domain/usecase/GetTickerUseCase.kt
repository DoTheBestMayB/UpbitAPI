package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.model.UpbitTickerData
import io.reactivex.rxjava3.core.Single

interface GetTickerUseCase {

    fun execute(market: String): Single<List<UpbitTickerData>>
}