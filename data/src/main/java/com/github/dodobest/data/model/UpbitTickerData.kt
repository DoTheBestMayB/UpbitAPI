package com.github.dodobest.data.model

import com.github.dodobest.domain.model.UpbitTickerData
import com.google.gson.annotations.SerializedName

data class UpbitTickerData(
    @SerializedName("market")
    val market: String, // 종목 구분 코드
    @SerializedName("opening_price")
    val openingPrice: Double, // 시가
    @SerializedName("trade_price")
    val tradePrice: Double, // 종가
    @SerializedName("signed_change_rate")
    val signedChangeRate: Double, // 부호가 있는 변화율
    @SerializedName("acc_trade_price_24h")
    val aacTradePrice24h: Double, // 24시간 누적 거래대금
) {
    fun toDomainData(): UpbitTickerData {
        return UpbitTickerData(
            market = market,
            openingPrice = openingPrice,
            tradePrice = tradePrice,
            signedChangeRate = signedChangeRate,
            aacTradePrice24h = aacTradePrice24h,
        )
    }
}
