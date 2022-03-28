package com.github.dodobest.data

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File
import java.util.concurrent.TimeUnit

class UpbitAPITest {
    private lateinit var server: MockWebServer
    private lateinit var service: UpbitAPI

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .connectTimeout(OkHttpClientConstant.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(OkHttpClientConstant.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(OkHttpClientConstant.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build())
            .build()
            .create()
    }

    @Test
    fun `getTicker 함수로 API 통신에 성공하면 UpbitTickerDataResponse 클래스로 변환된 데이터를 생성한다`() {
        // given
        val response = MockResponse()
            .setBody(File("src/test/resources/upbitTickerSuccessData.json").readText())
        server.enqueue(response)

        // when
        val actual = service.getTicker("KRW-BTC").subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .test()
            .awaitDone(3, TimeUnit.SECONDS)

        // then
        val expected = listOf(UpbitTickerData("KRW-BTC", 100.0, 150.0, 50.0,1000.0))
        actual.assertResult(expected)
    }

    @Test
    fun `getMarkets 함수로 API 통신에 성공하면 UpbitMarketDataResponse 클래스로 변환된 데이터를 생성한다`() {
        // given
        val response = MockResponse()
            .setBody(File("src/test/resources/upbitMarketSuccessData.json").readText())
        server.enqueue(response)

        // when
        val actual = service.getMarkets().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .test()
            .awaitDone(3, TimeUnit.SECONDS)

        // then
        val expected = listOf(UpbitMarketData("KRW-BTC", "비트코인", "Bitcoin", "NONE"),
            UpbitMarketData("KRW-ETH", "이더리움", "Ethereum", "NONE"),
            UpbitMarketData("KRW-NU", "누사이퍼", "Nucypher", "CAUTION")
        )
        actual.assertResult(expected)

    }
}