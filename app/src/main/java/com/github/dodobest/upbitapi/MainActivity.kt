package com.github.dodobest.upbitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.dodobest.upbitapi.databinding.ActivityMainBinding
import com.github.dodobest.upbitapi.model.MarketPlaceName
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupUI()
    }

    override fun onDestroy() {
        binding = null

        super.onDestroy()
    }

    private fun setupUI() {
        binding?.let {
            it.coinListViewPager.let { viewPager2 ->
                viewPager2.adapter = CoinListViewPagerAdapter(this)

                TabLayoutMediator(it.coinListTabLayout, viewPager2) { tab, position ->
                    tab.text = MarketPlaceName.from(position).toString()
                }.attach()
            }
        } ?: throw IllegalArgumentException(getString(R.string.view_binding_is_null))
    }
}