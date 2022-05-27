package com.github.dodobest.upbitapi

import com.github.dodobest.upbitapi.model.DataFormat
import java.text.DecimalFormat

object DataFormatHandler {

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