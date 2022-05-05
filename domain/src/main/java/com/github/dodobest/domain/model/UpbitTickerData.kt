package com.github.dodobest.domain.model

data class UpbitTickerData(
    val market: String,
    val openingPrice: Double,
    val tradePrice: Double,
    val signedChangeRate: Double,
    val aacTradePrice24h: Double,
)