package com.github.dodobest.upbitapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import com.github.dodobest.domain.model.UpbitTickerDataWithKoreanName
import com.github.dodobest.upbitapi.databinding.CoinItemBinding
import java.text.DecimalFormat

class UpbitAdapter : RecyclerView.Adapter<UpbitViewHolder>() {
    private val tickerResult: MutableList<UpbitTickerDataWithKoreanName> = mutableListOf()

    private val coinPriceFormat = DecimalFormat("#,###.########")
    private val changeRateFormat = DecimalFormat("#,###.##%")
    private val aacTradePriceFormat = DecimalFormat("#,###백만")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpbitViewHolder {
        val binding = CoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpbitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpbitViewHolder, position: Int) {
        holder.coinMarket.text = tickerResult[position].market
        holder.coinName.text = tickerResult[position].koreanName
        holder.coinPrice.text = coinPriceFormat.format(tickerResult[position].tradePrice)
        holder.changeRate.text =
            changeRateFormat.format(tickerResult[position].signedChangeRate).toString()
        holder.aacTradePrice.text =
            aacTradePriceFormat.format(tickerResult[position].aacTradePrice24h / 1000000).toString()
    }

    override fun getItemCount(): Int {
        return tickerResult.size
    }

    fun setResult(tickerData: List<UpbitTickerDataWithKoreanName>) {
        if (tickerData == tickerResult) return

        tickerData.forEach {
            if (tickerResult.indexOf(it) == -1) {
                tickerResult.add(it)
                notifyItemInserted(tickerResult.size - 1)
            } else if (tickerResult[tickerResult.indexOf(it)] != it) {
                tickerResult[tickerResult.indexOf(it)] = it
                notifyItemChanged(tickerResult.indexOf(it))
            }
        }
    }
}