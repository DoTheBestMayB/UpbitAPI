package com.github.dodobest.upbitapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.domain.model.UpbitTickerDataWithKoreanName
import com.github.dodobest.upbitapi.databinding.CoinItemBinding
import javax.inject.Inject

class UpbitAdapter @Inject constructor(
    private val dataFormatHandler: DataFormatHandler,
) : RecyclerView.Adapter<UpbitViewHolder>() {
    private val tickerResult: MutableList<UpbitTickerDataWithKoreanName> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpbitViewHolder {
        val binding = CoinItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UpbitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpbitViewHolder, position: Int) {
        holder.coinMarket.text = tickerResult[position].market
        holder.coinName.text = tickerResult[position].koreanName
        holder.coinPrice.text = dataFormatHandler.formatCoinPrice(tickerResult[position].tradePrice)
        holder.changeRate.text = dataFormatHandler.formatChangeRate(
            tickerResult[position].signedChangeRate
        )
        holder.aacTradePrice.text = dataFormatHandler.formatAacTradePrice(
            tickerResult[position].aacTradePrice24h / 1000000
        )
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