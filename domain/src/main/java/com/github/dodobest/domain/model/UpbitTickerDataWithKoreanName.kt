package com.github.dodobest.domain.model

data class UpbitTickerDataWithKoreanName(
    val market: String,
    val koreanName: String,
    val openingPrice: Double,
    val tradePrice: Double,
    val signedChangeRate: Double,
    val aacTradePrice24h: Double,
)
