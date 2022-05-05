package com.github.dodobest.upbitapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.domain.model.UpbitTickerData
import com.github.dodobest.upbitapi.databinding.CoinItemBinding
import java.text.DecimalFormat

class UpbitAdapter : RecyclerView.Adapter<UpbitViewHolder>() {
    private val result: MutableList<UpbitTickerData> = mutableListOf()
    private val decimalFormat = DecimalFormat("#,###.########")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpbitViewHolder {
        val binding = CoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpbitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpbitViewHolder, position: Int) {
        holder.coinName.text = result[position].market
        holder.coinPrice.text = decimalFormat.format(result[position].tradePrice)
    }

    override fun getItemCount(): Int {
        return result.size
    }

    fun setResult(newResult: List<UpbitTickerData>) {
        if (result == newResult) {
            return
        }
        newResult.forEach {
            if (result.indexOf(it) == -1) {
                result.add(it)
                notifyItemInserted(result.size-1)
            } else if (result[result.indexOf(it)] != it) {
                result[result.indexOf(it)] = it
                notifyItemChanged(result.indexOf(it))
            }
        }
    }
}