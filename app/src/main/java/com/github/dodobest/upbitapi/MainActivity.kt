package com.github.dodobest.upbitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dodobest.upbitapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var upbitAdapter: UpbitAdapter
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
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        AppCompatResources.getDrawable(this, R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
        binding.recyclerView.addItemDecoration(divider)
    }

    private fun setAdapter() {
        upbitAdapter = UpbitAdapter()
        binding.recyclerView.adapter = upbitAdapter
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