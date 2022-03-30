package com.github.dodobest.data.model

import com.google.gson.annotations.SerializedName

internal data class UpbitMarketData(
    @SerializedName("market")
    val market: String,
    @SerializedName("korean_name")
    val koreanName: String,
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("market_warning")
    val marketWarning: String
)
