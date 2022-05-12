package com.github.dodobest.upbitapi

import java.text.DecimalFormat

object DataFormatHandler {

    private val coinPriceFormat = DecimalFormat("#,###.########")
    private val changeRateFormat = DecimalFormat("#,###.##%")
    private val aacTradePriceFormat = DecimalFormat("#,###백만")

    fun formatCoinPrice(price: Double): String {
        return coinPriceFormat.format(price)
    }

    fun formatChangeRate(rate: Double): String {
        return changeRateFormat.format(rate)
    }

    fun formatAacTradePrice(tradePrice: Double): String {
        return aacTradePriceFormat.format(tradePrice)
    }
}