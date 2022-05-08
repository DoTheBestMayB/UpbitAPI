package com.github.dodobest.upbitapi

import java.text.DecimalFormat
import javax.inject.Inject

class DataFormatHandlerImpl @Inject constructor() : DataFormatHandler {
    override fun formatCoinPrice(price: Double): String {
        return coinPriceFormat.format(price)
    }

    override fun formatChangeRate(rate: Double): String {
        return changeRateFormat.format(rate)
    }

    override fun formatAacTradePrice(tradePrice: Double): String {
        return aacTradePriceFormat.format(tradePrice)
    }

    companion object {
        private val coinPriceFormat = DecimalFormat("#,###.########")
        private val changeRateFormat = DecimalFormat("#,###.##%")
        private val aacTradePriceFormat = DecimalFormat("#,###백만")
    }
}