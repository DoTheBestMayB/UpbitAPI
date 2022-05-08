package com.github.dodobest.upbitapi

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.domain.model.UpbitTickerDataWithKoreanName
import com.github.dodobest.upbitapi.databinding.CoinItemBinding

class UpbitViewHolder(
    binding: CoinItemBinding,
    private val dataFormatHandler: DataFormatHandler
) : RecyclerView.ViewHolder(binding.root) {
    private val coinName: TextView = binding.coinName
    private val coinMarket: TextView = binding.coinMarket
    private val coinPrice: TextView = binding.coinPrice
    private val changeRate: TextView = binding.changeRate
    private val aacTradePrice: TextView = binding.aacTradePrice

    fun setData(tickerResult: UpbitTickerDataWithKoreanName) {
        coinMarket.text = tickerResult.market
        coinName.text = tickerResult.koreanName
        coinPrice.text = dataFormatHandler.formatCoinPrice(tickerResult.tradePrice)
        changeRate.text = dataFormatHandler.formatChangeRate(
            tickerResult.signedChangeRate
        )
        aacTradePrice.text = dataFormatHandler.formatAacTradePrice(
            tickerResult.aacTradePrice24h / 1_000_000
        )
    }
}
