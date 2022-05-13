package com.github.dodobest.upbitapi

import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.upbitapi.databinding.CoinItemBinding
import com.github.dodobest.upbitapi.model.UpbitTickerDataWithKoreanName

class UpbitViewHolder(
    private val binding: CoinItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun setData(tickerResult: UpbitTickerDataWithKoreanName) {
        binding.ticker = tickerResult
    }
}
