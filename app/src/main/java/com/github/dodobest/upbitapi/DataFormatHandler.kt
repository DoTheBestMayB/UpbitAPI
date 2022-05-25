package com.github.dodobest.upbitapi

import com.github.dodobest.upbitapi.model.DataFormat
import com.github.dodobest.upbitapi.model.MarketPlaceName
import java.text.DecimalFormat
import kotlin.IllegalArgumentException

object DataFormatHandler {

    private val krwDataFormat = DataFormat(
        "#,###.##%", "#,###.########",
        1_000_000, "#,###백만"
    )
    private val btcDataFormat = DataFormat(
        "#,###.##%", "#,###.########",
        1, "#,###.###"
    )
    private val usdtDataFormat = DataFormat(
        "#,###.##%", "#,###.###",
        1, "#,###.###"
    )

    private val dataFormat = mapOf(
        "KRW" to krwDataFormat, "BTC" to btcDataFormat, "USDT" to usdtDataFormat
    )

    fun formatCoinPrice(price: Double, marketPlaceName: MarketPlaceName): String {
        return dataFormat[marketPlaceName.toString()]?.let {
            DecimalFormat(it.priceFormat).format(price)
        } ?: throw IllegalArgumentException()
    }

    fun formatChangeRate(rate: Double, marketPlaceName: MarketPlaceName): String {
        return dataFormat[marketPlaceName.toString()]?.let {
            DecimalFormat(it.changeRateFormat).format(rate)
        } ?: throw IllegalArgumentException()
    }

    fun formatAacTradePrice(tradePrice: Double, marketPlaceName: MarketPlaceName): String {
        return dataFormat[marketPlaceName.toString()]?.let {
            DecimalFormat(it.aacTradeVolumeFormat).format(tradePrice / it.aacTradeVolumeUnit)
        } ?: throw IllegalArgumentException()
    }
}