package com.github.dodobest.upbitapi

import com.github.dodobest.upbitapi.model.MarketPlaceName
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DataFormatHandlerTest {

    @Test
    fun `숫자를 formatCoinPrice 함수에 넣으면 KRW 마켓에 맞는 숫자로 변환한다`() {
        // given
        val inputNum = 10_000_000.123_456
        val marketPlaceName = MarketPlaceName.KRW

        // when
        val result = DataFormatHandler.formatCoinPrice(inputNum, marketPlaceName)

        // then
        val expected = "10,000,000.123456"
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `숫자를 formatChangeRate 함수에 넣으면 KRW 마켓에 맞는 숫자로 변환한다`() {
        // given
        val inputNum = 10.123456
        val marketPlaceName = MarketPlaceName.KRW

        // when
        val result = DataFormatHandler.formatChangeRate(inputNum, marketPlaceName)

        // then
        val expected = "1,012.35%"
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `숫자를 formatAacTradePrice 함수에 넣으면 KRW 마켓에 맞는 숫자로 변환한다`() {
        // given
        val inputNum = 10_000_000_000.123456
        val marketPlaceName = MarketPlaceName.KRW

        // when
        val result = DataFormatHandler.formatAacTradePrice(inputNum, marketPlaceName)

        // then
        val expected = "10,000백만"
        assertThat(result).isEqualTo(expected)
    }
}