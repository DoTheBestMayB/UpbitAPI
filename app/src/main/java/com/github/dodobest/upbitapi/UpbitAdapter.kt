package com.github.dodobest.upbitapi

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.domain.model.UpbitTickerDataWithKoreanName
import com.github.dodobest.upbitapi.databinding.CoinItemBinding

class UpbitAdapter(
    private val dataFormatHandler: DataFormatHandler,
) : RecyclerView.Adapter<UpbitViewHolder>() {
    private var tickerResult: List<UpbitTickerDataWithKoreanName> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpbitViewHolder {
        val binding = CoinItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UpbitViewHolder(binding, dataFormatHandler)
    }

    override fun onBindViewHolder(holder: UpbitViewHolder, position: Int) {
        holder.setData(tickerResult[position])
    }

    override fun getItemCount(): Int {
        return tickerResult.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setResult(tickerData: List<UpbitTickerDataWithKoreanName>) {
        tickerResult = tickerData
        notifyDataSetChanged()
    }
}