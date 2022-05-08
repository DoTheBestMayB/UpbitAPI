package com.github.dodobest.upbitapi

interface DataFormatHandler {
    fun formatCoinPrice(price: Double) : String
    fun formatChangeRate(rate: Double) : String
    fun formatAacTradePrice(tradePrice: Double) : String
}