package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.model.UpbitMarketData
import io.reactivex.rxjava3.core.Single

interface GetMarketsUseCase {
    operator fun invoke() : Single<List<UpbitMarketData>>
}