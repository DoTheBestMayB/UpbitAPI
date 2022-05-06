package com.github.dodobest.upbitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dodobest.upbitapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var upbitAdapter: UpbitAdapter
    private val viewModel: UpbitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTimber()
        setDataBinding()
        setRecyclerView()
        setAdapter()
        setLiveDataObserve()
    }

    private fun setLiveDataObserve() {
        viewModel.tickers.observe(this) {
            upbitAdapter.setResult(it)
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(AppCompatResources.getDrawable(this, R.drawable.divider)!!)
        binding.recyclerView.addItemDecoration(divider)
    }

    private fun setAdapter() {
        upbitAdapter = UpbitAdapter()
        binding.recyclerView.adapter = upbitAdapter
    }

    private fun setTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setDataBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}