package com.github.dodobest.upbitapi

import com.github.dodobest.upbitapi.model.DataFormat
import java.text.DecimalFormat

object DataFormatHandler {

    fun formatCoinPrice(price: Double, dataFormat: DataFormat): String {
        return DecimalFormat(dataFormat.priceFormat).format(price)
    }

    fun formatChangeRate(rate: Double, dataFormat: DataFormat): String {
        return DecimalFormat(dataFormat.changeRateFormat).format(rate)
    }

    fun formatAacTradePrice(tradePrice: Double, dataFormat: DataFormat): String {
        return DecimalFormat(dataFormat.aacTradeVolumeFormat).format(tradePrice / dataFormat.aacTradeVolumeUnit)
    }
}