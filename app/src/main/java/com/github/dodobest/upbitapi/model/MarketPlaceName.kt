package com.github.dodobest.upbitapi.model

enum class MarketPlaceName(val position: Int) {
    KRW(0),
    BTC(1),
    USDT(2),
    NEW(3);

    companion object {
        fun from(position: Int): MarketPlaceName? = values().find { it.position == position }
    }
}