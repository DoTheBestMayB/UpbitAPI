package com.github.dodobest.upbitapi.model

import com.github.dodobest.data.model.UpbitMarketData
import com.github.dodobest.data.model.UpbitTickerData

object UpbitFakeRemoteDataSet {

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

    private val upbitBTCTickerData = listOf(
        UpbitTickerData(
            "KRW-BTC",
            8_450_000.0,
            8_621_000.0,
            171_000.0,
            232_702_901_371.093_08,
        ),
    )

    private val upbitETHTickerData = listOf(
        UpbitTickerData(
            "KRW-ETH",
            1_000.0,
            1_500.0,
            500.0,
            6_000.0,
        ),
    )

    private val upbitNUTickerData = listOf(
        UpbitTickerData(
            "KRW-NU",
            10.0,
            5.0,
            -5.0,
            20_000.0,
        ),
    )

    val upbitTickerData = listOf(
        upbitBTCTickerData.first().toDomainData(),
        upbitETHTickerData.first().toDomainData(),
        upbitNUTickerData.first().toDomainData(),
    )

    val upbitTickerDataWithKoreanName = listOf(
        UpbitTickerDataWithKoreanName.fromUpbitTickerData(
            upbitBTCTickerData.first().toDomainData(),
            "비트코인",
        ),
        UpbitTickerDataWithKoreanName.fromUpbitTickerData(
            upbitETHTickerData.first().toDomainData(),
            "이더리움",
        ),
        UpbitTickerDataWithKoreanName.fromUpbitTickerData(
            upbitNUTickerData.first().toDomainData(),
            "누사이퍼",
        ),
    )
}