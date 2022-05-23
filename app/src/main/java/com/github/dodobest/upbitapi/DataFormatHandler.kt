package com.github.dodobest.upbitapi

import java.lang.IllegalArgumentException
import java.text.DecimalFormat

object DataFormatHandler {
    private const val NO_EXIST_COIN = "등록되지 않은 마켓입니다."

    private val coinPriceDivider = listOf(1_000_000, 1, 1)
    private val coinPriceFormat = listOf(
        DecimalFormat("#,###.########"),
        DecimalFormat("#,###.########"),
        DecimalFormat("#,###.###")
    )
    private val changeRateFormat = DecimalFormat("#,###.##%")
    private val aacTradePriceFormat = listOf(
        DecimalFormat("#,###백만"),
        DecimalFormat("#,###.###"),
        DecimalFormat("#,###.###"),
    )

    fun formatCoinPrice(price: Double, marketIndex: Int): String {
        if (marketIndex == -1) throw IllegalArgumentException(NO_EXIST_COIN)

        return coinPriceFormat[marketIndex].format(price)
    }

    fun formatChangeRate(rate: Double): String {
        return changeRateFormat.format(rate)
    }

    fun formatAacTradePrice(tradePrice: Double, marketIndex: Int): String {
        if (marketIndex == -1) throw IllegalArgumentException(NO_EXIST_COIN)

        return aacTradePriceFormat[marketIndex].format(
            tradePrice / coinPriceDivider[marketIndex]
        )
    }
}