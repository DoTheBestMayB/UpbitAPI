package com.github.dodobest.upbitapi.model

import com.github.dodobest.domain.model.UpbitTickerData

data class UpbitTickerDataWithKoreanName(
    val market: String,
    val koreanName: String,
    val openingPrice: Double,
    val tradePrice: Double,
    val signedChangeRate: Double,
    val aacTradePrice24h: Double,
) {
    companion object {
        fun fromUpbitTickerData(
            upbitTickerData: UpbitTickerData,
            koreanName: String
        ): UpbitTickerDataWithKoreanName {
            return UpbitTickerDataWithKoreanName(
                upbitTickerData.market,
                koreanName,
                upbitTickerData.openingPrice,
                upbitTickerData.tradePrice,
                upbitTickerData.signedChangeRate,
                upbitTickerData.aacTradePrice24h,
            )
        }

    }
}
