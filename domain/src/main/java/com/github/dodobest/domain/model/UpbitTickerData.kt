package com.github.dodobest.domain.model

data class UpbitTickerData(
    val market: String,
    val openingPrice: Double,
    val tradePrice: Double,
    val signedChangeRate: Double,
    val aacTradePrice24h: Double,
) {
    fun addKoreanName(koreanName: String): UpbitTickerDataWithKoreanName {
        return  UpbitTickerDataWithKoreanName(
            market, koreanName, openingPrice, tradePrice, signedChangeRate, aacTradePrice24h
        )
    }
}