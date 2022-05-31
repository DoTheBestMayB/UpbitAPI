package com.github.dodobest.upbitapi

import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.upbitapi.databinding.CoinItemBinding
import com.github.dodobest.upbitapi.model.UpbitTickerDataForUI

class UpbitViewHolder(
    private var binding: CoinItemBinding?,
) : RecyclerView.ViewHolder(binding?.root ?: throw IllegalArgumentException()) {

    private val _binding get() = binding ?: throw IllegalArgumentException()

    fun setData(tickerResult: UpbitTickerDataForUI) {
        _binding.ticker = tickerResult
    }

    fun releaseBinding() {
        binding = null
    }
}