package com.github.dodobest.upbitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.dodobest.upbitapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val upbitAdapter: UpbitAdapter = UpbitAdapter(DataFormatHandler())
    private val viewModel: UpbitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDataBinding()
        setRecyclerView()
        setAdapter()
        setLiveDataObserve()
        loadInitialContent()
    }

    private fun setDataBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setRecyclerView() {
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        AppCompatResources.getDrawable(this, R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
        binding.coinPriceRecyclerView.addItemDecoration(divider)
    }

    private fun setAdapter() {
        binding.coinPriceRecyclerView.adapter = upbitAdapter
    }

    private fun setLiveDataObserve() {
        viewModel.tickers.observe(this) {
            upbitAdapter.setResult(it)
        }
    }

    private fun loadInitialContent() {
        viewModel.getMarkets()
    }
}