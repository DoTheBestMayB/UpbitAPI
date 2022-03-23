package com.github.dodobest.domain

data class UpbitTickerData(
    val market: String, // 종목 구분 코드
    val openingPrice: String, // 시가
    val tradePrice: String, // 종가
    val signedChangePrice: Double, // 부호가 있는 변화액
    val aacTradePrice24h: Double, // 24시간 누적 거래대금
)
