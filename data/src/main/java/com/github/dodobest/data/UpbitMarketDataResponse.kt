package com.github.dodobest.data

import com.github.dodobest.domain.UpbitMarketData
import com.google.gson.annotations.SerializedName

data class UpbitMarketDataResponse(
    val market: String,
    @SerializedName("korean_name")
    val koreanName: String,
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("market_warning")
    val marketWarning: String
) {
    fun toData() : UpbitMarketData {
        return UpbitMarketData(market, koreanName, englishName, marketWarning)
    }
}
