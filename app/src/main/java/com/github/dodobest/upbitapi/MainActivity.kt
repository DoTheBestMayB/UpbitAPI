package com.github.dodobest.upbitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.github.dodobest.upbitapi.databinding.ActivityMainBinding
import com.github.dodobest.upbitapi.model.MarketPlaceName
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        viewPager = binding.coinListViewPager
        viewPager.adapter = CoinListViewPagerAdapter(this)

        TabLayoutMediator(binding.coinListTabLayout, viewPager) { tab, position ->
            tab.text = MarketPlaceName.from(position).toString()
        }.attach()
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}