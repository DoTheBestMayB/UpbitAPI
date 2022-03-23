package com.github.dodobest.data

import com.google.gson.annotations.SerializedName

data class UpbitTickerDataResponse(
    val market: String, // 종목 구분 코드
    @SerializedName("opening_price")
    val openingPrice: String, // 시가
    @SerializedName("trade_price")
    val tradePrice: String, // 종가
    @SerializedName("signed_change_price")
    val signedChangePrice: Double, // 부호가 있는 변화액
    @SerializedName("acc_trade_price_24h")
    val aacTradePrice24h: Double, // 24시간 누적 거래대금
)
