package com.github.dodobest.upbitapi.model

import com.github.dodobest.domain.model.UpbitTickerData

data class UpbitTickerDataForUI(
    val market: String,
    val koreanName: String,
    val openingPrice: Double,
    val tradePrice: String,
    val signedChangeRate: String,
    val aacTradePrice24h: String,
) {
    companion object {
        fun fromUpbitTickerData(
            upbitTickerData: UpbitTickerData,
            koreanName: String,
            tradePrice: String,
            signedChangeRate: String,
        ): UpbitTickerDataForUI {
            return UpbitTickerDataForUI(
                upbitTickerData.market,
                koreanName,
                upbitTickerData.openingPrice,
                tradePrice,
                signedChangeRate,
                upbitTickerData.aacTradePrice24h.toString(),
            )
        }

        fun convertAacTradePrice(
            it: UpbitTickerDataForUI,
            aacTradePrice24h: String,
        ): UpbitTickerDataForUI {
            return UpbitTickerDataForUI(
                it.market,
                it.koreanName,
                it.openingPrice,
                it.tradePrice,
                it.signedChangeRate,
                aacTradePrice24h,
            )
        }
    }
}
