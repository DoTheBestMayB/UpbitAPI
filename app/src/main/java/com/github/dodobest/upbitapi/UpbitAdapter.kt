package com.github.dodobest.upbitapi

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.upbitapi.databinding.CoinItemBinding
import com.github.dodobest.upbitapi.model.UpbitTickerDataWithKoreanName

class UpbitAdapter : RecyclerView.Adapter<UpbitViewHolder>() {

    private val tickerResult: MutableList<UpbitTickerDataWithKoreanName> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpbitViewHolder {
        val binding = CoinItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        binding.formatHandler = DataFormatHandler
        return UpbitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpbitViewHolder, position: Int) {
        holder.setData(tickerResult[position])
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        for (index in recyclerView.size downTo 0) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(index)
            if (viewHolder is UpbitViewHolder) {
                viewHolder.releaseBinding()
            }
        }

        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun getItemCount(): Int {
        return tickerResult.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setResult(tickerData: List<UpbitTickerDataWithKoreanName>) {
        tickerResult.clear()
        tickerResult.addAll(tickerData)
        notifyDataSetChanged()
    }
}