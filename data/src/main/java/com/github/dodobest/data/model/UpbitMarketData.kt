package com.github.dodobest.data.model

import com.github.dodobest.domain.model.UpbitMarketData
import com.google.gson.annotations.SerializedName

data class UpbitMarketData(
    @SerializedName("market")
    val market: String,
    @SerializedName("korean_name")
    val koreanName: String,
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("market_warning")
    val marketWarning: String,
) {

    fun toDomainData(): UpbitMarketData {
        return UpbitMarketData(
            market = market,
            koreanName = koreanName,
            englishName = englishName,
            marketWarning = marketWarning,
        )
    }
}
