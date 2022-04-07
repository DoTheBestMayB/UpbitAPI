package com.github.dodobest.data.remote

import com.github.dodobest.data.Constant
import com.github.dodobest.data.model.UpbitMarketData
import com.github.dodobest.data.model.UpbitTickerData
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

internal class UpbitAPITest {
    private lateinit var server: MockWebServer
    private lateinit var upbitAPI: UpbitAPI

    @Before
    fun setUp() {
        server = MockWebServer()
        upbitAPI = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .connectTimeout(Constant.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constant.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build())
            .build()
            .create(UpbitAPI::class.java)
    }

    @Test
    fun `getTicker 함수로 API 통신에 성공하면 UpbitTickerDataResponse 클래스로 변환된 데이터를 생성한다`() {
        // given
        val response = MockResponse()
            .setBody(File("src/test/resources/upbitTickerSuccessData.json").readText())
        server.enqueue(response)
        val expected = listOf(UpbitTickerData(
            market = "KRW-BTC",
            openingPrice = 100.0,
            tradePrice = 150.0,
            signedChangePrice = 50.0,
            aacTradePrice24h = 1000.0)
        )

        // when - then
        upbitAPI.getTicker("KRW-BTC")
            .test()
            .assertValue(expected)
    }

    @Test
    fun `getMarkets 함수로 API 통신에 성공하면 UpbitMarketDataResponse 클래스로 변환된 데이터를 생성한다`() {
        // given
        val response = MockResponse()
            .setBody(File("src/test/resources/upbitMarketSuccessData.json").readText())
        server.enqueue(response)
        val expected = listOf(
            UpbitMarketData(
                market = "KRW-BTC",
                koreanName = "비트코인",
                englishName = "Bitcoin",
                marketWarning = "NONE"),
            UpbitMarketData(
                market = "KRW-ETH",
                koreanName = "이더리움",
                englishName = "Ethereum",
                marketWarning = "NONE"),
            UpbitMarketData(
                market = "KRW-NU",
                koreanName = "누사이퍼",
                englishName = "Nucypher",
                marketWarning = "CAUTION")
        )

        // when - then
        upbitAPI.getMarkets()
            .test()
            .assertValue(expected)
    }
}