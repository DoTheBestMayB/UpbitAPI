package com.github.dodobest.upbitapi

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.upbitapi.databinding.CoinItemBinding

class UpbitViewHolder(binding: CoinItemBinding): RecyclerView.ViewHolder(binding.root) {
    val coinName: TextView = binding.coinName
    val coinPrice: TextView = binding.coinPrice
    val changeRate: TextView = binding.changeRate
    val aacTradePrice: TextView = binding.aacTradePrice
}
