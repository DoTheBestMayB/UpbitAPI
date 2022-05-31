package com.github.dodobest.upbitapi

import com.github.dodobest.upbitapi.model.DataFormat
import com.github.dodobest.upbitapi.model.MarketPlaceName
import java.text.DecimalFormat

object DataFormatHandler {

    private var krwDataFormat = DataFormat(
        changeRateFormat = "#,###.##%", priceFormat = "#,###.########"
    )

    private val btcDataFormat = DataFormat(
        changeRateFormat = "#,###.##%", priceFormat = "#,###.########",
    )

    private val usdtDataFormat = DataFormat(
        changeRateFormat = "#,###.##%", priceFormat = "#,###.###",
    )

    val newDataFormat = DataFormat(
        changeRateFormat = "#,###.##%", priceFormat = "#,###.########",
    )

    val dataFormat = mapOf(
        MarketPlaceName.KRW to krwDataFormat,
        MarketPlaceName.BTC to btcDataFormat,
        MarketPlaceName.USDT to usdtDataFormat,
    )

    fun formatTradePrice(tradePrice: Double, dataFormat: DataFormat): String {
        return DecimalFormat(dataFormat.priceFormat).format(tradePrice)
    }

    fun formatChangeRate(rate: Double, dataFormat: DataFormat): String {
        return DecimalFormat(dataFormat.changeRateFormat).format(rate)
    }

    fun formatAacTradePrice(aacTradePrice: Double, dataFormat: DataFormat): String {
        return DecimalFormat(dataFormat.aacTradeVolumeFormat)
            .format(aacTradePrice / dataFormat.aacTradeVolumeUnit)
    }
}