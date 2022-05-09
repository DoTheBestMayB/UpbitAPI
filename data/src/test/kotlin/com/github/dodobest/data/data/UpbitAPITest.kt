package com.github.dodobest.data.data

import com.github.dodobest.data.model.UpbitMarketData
import com.github.dodobest.data.model.UpbitTickerData
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.io.File

import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class UpbitAPITest {
    private lateinit var server: MockWebServer
    private lateinit var baseUrl: HttpUrl
    private lateinit var retrofit: Retrofit
    private lateinit var upbitAPI: UpbitAPI

    private val upbitTickerDataValue = listOf(100.0, 150.0, 50.0, 1000.0)
    private val upbitMarketDataBTC = listOf("KRW-BTC", "비트코인", "Bitcoin", "NONE")
    private val upbitMarketDataETH = listOf("KRW-ETH", "이더리움", "Ethereum", "NONE")
    private val upbitMarketDataNU = listOf("KRW-NU", "누사이퍼", "Nucypher", "CAUTION")

    private val upbitTickerSuccessDataPath = "src/test/resources/upbitTickerSuccessData.json"
    private val upbitMarketSuccessDataPath = "src/test/resources/upbitMarketSuccessData.json"
    private val upbitTickerDataCoinName = "KRW-BTC"

    @Before
    fun setUp() {
        server = MockWebServer()
        baseUrl = server.url("")
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl.toString())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
        upbitAPI = retrofit.create(UpbitAPI::class.java)
    }

    @Test
    fun `getTicker 함수로 API 통신에 성공하면 UpbitTickerDataResponse 클래스로 변환된 데이터를 생성한다`() {
        // given
        val response = MockResponse()
            .setBody(File(upbitTickerSuccessDataPath).readText())
        server.enqueue(response)
        val expected = listOf(
            UpbitTickerData(
                market = upbitTickerDataCoinName,
                openingPrice = upbitTickerDataValue[0],
                tradePrice = upbitTickerDataValue[1],
                signedChangeRate = upbitTickerDataValue[2],
                aacTradePrice24h = upbitTickerDataValue[3],
            )
        )

        // when - then
        upbitAPI.getTicker(upbitTickerDataCoinName)
            .test()
            .assertValue(expected)
    }

    @Test
    fun `getMarkets 함수로 API 통신에 성공하면 UpbitMarketDataResponse 클래스로 변환된 데이터를 생성한다`() {
        // given
        val response = MockResponse()
            .setBody(File(upbitMarketSuccessDataPath).readText())
        server.enqueue(response)
        val expected = listOf(
            UpbitMarketData(
                market = upbitMarketDataBTC[0],
                koreanName = upbitMarketDataBTC[1],
                englishName = upbitMarketDataBTC[2],
                marketWarning = upbitMarketDataBTC[3],
            ),
            UpbitMarketData(
                market = upbitMarketDataETH[0],
                koreanName = upbitMarketDataETH[1],
                englishName = upbitMarketDataETH[2],
                marketWarning = upbitMarketDataETH[3],
            ),
            UpbitMarketData(
                market = upbitMarketDataNU[0],
                koreanName = upbitMarketDataNU[1],
                englishName = upbitMarketDataNU[2],
                marketWarning = upbitMarketDataNU[3],
            )
        )

        // when - then
        upbitAPI.getMarkets()
            .test()
            .assertValue(expected)
    }
}