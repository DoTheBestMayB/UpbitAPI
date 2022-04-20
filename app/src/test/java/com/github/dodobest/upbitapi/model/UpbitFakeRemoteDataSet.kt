package com.github.dodobest.upbitapi.model

import com.github.dodobest.data.model.UpbitMarketData
import com.github.dodobest.data.model.UpbitTickerData

object UpbitFakeRemoteDataSet {
    private const val BTC_CODE_NAME = "KRW-BTC"
    private const val ETH_CODE_NAME = "KRW-ETH"
    private const val NU_CODE_NAME = "KRW-NU"

    val upbitMarketData = listOf(
        UpbitMarketData(
            "KRW-BTC",
            "비트코인",
            "Bitcoin",
            "None",
        ),
        UpbitMarketData(
            "KRW-ETH",
            "이더리움",
            "Ethereum",
            "NONE",
        ),
        UpbitMarketData(
            "KRW-NU",
            "누사이퍼",
            "Nucypher",
            "CAUTION",
        ),
    )

    val upbitBTCTickerData = listOf(
        UpbitTickerData(
            "KRW-BTC",
            8450000.0,
            8621000.0,
            171000.0,
            232702901371.09308,
        ),
    )

    val upbitETHTickerData = listOf(
        UpbitTickerData(
            "KRW-ETH",
            1000.0,
            1500.0,
            500.0,
            6000.0,
        ),
    )

    val upbitNUTickerData = listOf(
        UpbitTickerData(
            "KRW-NU",
            10.0,
            5.0,
            -5.0,
            20000.0,
        ),
    )

    val upbitTickerData = mapOf(
        BTC_CODE_NAME to upbitBTCTickerData[0].toDomainData(),
        ETH_CODE_NAME to upbitETHTickerData[0].toDomainData(),
        NU_CODE_NAME to upbitNUTickerData[0].toDomainData(),
    )
}