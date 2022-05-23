package com.github.dodobest.upbitapi

import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.upbitapi.databinding.CoinItemBinding
import com.github.dodobest.upbitapi.model.UpbitTickerDataWithKoreanName

class UpbitViewHolder(
    private var _binding: CoinItemBinding?,
) : RecyclerView.ViewHolder(_binding?.root ?: throw IllegalArgumentException()) {
    private val binding get() = _binding!!

    fun setData(tickerResult: UpbitTickerDataWithKoreanName) {
        binding.ticker = tickerResult
    }

    fun releaseBinding() {
        _binding = null
    }
}