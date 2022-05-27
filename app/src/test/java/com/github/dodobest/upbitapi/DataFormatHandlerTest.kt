package com.github.dodobest.upbitapi

import android.content.Context
import com.github.dodobest.upbitapi.model.DataFormat
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class DataFormatHandlerTest {

    lateinit var dataFormat: DataFormat
    lateinit var mockContext: Context

    @Before
    fun setUp() {
        mockContext = mockk<Context>()

        every {
            mockContext.getString(R.string.krw_aac_trade_volume_format)
        } returns "#,###백만"

        dataFormat = DataFormat(
            "#,###.##%", "#,###.########",
            1_000_000, mockContext.getString(R.string.krw_aac_trade_volume_format)
        )
    }

    @Test
    fun `숫자를 formatCoinPrice 함수에 넣으면 KRW 마켓에 맞는 숫자로 변환한다`() {
        // given
        val inputNum = 10_000_000.123_456

        // when
        val result = DataFormatHandler.formatTradePrice(inputNum, dataFormat)

        // then
        val expected = "10,000,000.123456"
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `숫자를 formatChangeRate 함수에 넣으면 KRW 마켓에 맞는 숫자로 변환한다`() {
        // given
        val inputNum = 10.123456

        // when
        val result = DataFormatHandler.formatChangeRate(inputNum, dataFormat)

        // then
        val expected = "1,012.35%"
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `숫자를 formatAacTradePrice 함수에 넣으면 KRW 마켓에 맞는 숫자로 변환한다`() {
        // given
        val inputNum = 10_000_000_000.123456

        // when
        val result = DataFormatHandler.formatAacTradePrice(inputNum, dataFormat)

        // then
        val expected = "10,000백만"
        assertThat(result).isEqualTo(expected)
    }
}