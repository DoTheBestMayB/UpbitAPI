package com.github.dodobest.upbitapi

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DataFormatHandlerTest {

    @Test
    fun `10000000_123456을 formatCoinPrice 함수에 넣으면 KRW 마켓에서 10,000,000_123456을 만든다`() {
        // given
        val inputNum = 10_000_000.123_456
        val marketIndex = Constant.marketIndex.indexOf("KRW")

        // when
        val result = DataFormatHandler.formatCoinPrice(inputNum, marketIndex)

        // then
        val expected = "10,000,000.123456"
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `10_123을 formatChangeRate 함수에 넣으면 1,000_12를 만든다`() {
        // given
        val inputNum = 10.123456

        // when
        val result = DataFormatHandler.formatChangeRate(inputNum)

        // then
        val expected = "1,012.35%"
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `10000000000_123456을 formatAacTradePrice 함수에 넣으면 KRW 마켓에서 10,000백만을 만든다`() {
        // given
        val inputNum = 10_000_000_000.123456
        val marketIndex = Constant.marketIndex.indexOf("KRW")

        // when
        val result = DataFormatHandler.formatAacTradePrice(inputNum, marketIndex)

        // then
        val expected = "10,000백만"
        assertThat(result).isEqualTo(expected)
    }
}