package com.github.dodobest.upbitapi.model

import com.github.dodobest.domain.model.UpbitTickerData
import com.github.dodobest.upbitapi.Constant

data class UpbitTickerDataWithKoreanName(
    val market: String,
    val koreanName: String,
    val openingPrice: Double,
    val tradePrice: Double,
    val signedChangeRate: Double,
    val aacTradePrice24h: Double,
    val marketIndex: Int,
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
                Constant.marketIndex.indexOf(upbitTickerData.market.split("-").first()),
            )
        }

    }
}
