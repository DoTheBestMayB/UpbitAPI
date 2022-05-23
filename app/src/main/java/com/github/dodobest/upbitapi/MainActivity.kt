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

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val upbitAdapter: UpbitAdapter = UpbitAdapter()
    private val viewModel: UpbitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
        setLiveDataObserve()
        loadInitialContent()
    }

    private fun setRecyclerView() {
        binding.coinPriceRecyclerView.adapter = upbitAdapter

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        AppCompatResources.getDrawable(this, R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
        binding.coinPriceRecyclerView.addItemDecoration(divider)
    }

    private fun setLiveDataObserve() {
        viewModel.tickers.observe(this) {
            upbitAdapter.setResult(it)
        }
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }

    private fun loadInitialContent() {
        viewModel.getMarkets()
    }
}