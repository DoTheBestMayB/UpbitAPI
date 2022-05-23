package com.github.dodobest.upbitapi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CoinListViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val marketPlaceName = listOf("KRW", "BTC", "USDT")

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return CoinListPageFragment(marketPlaceName[position])
    }

    companion object {
        private const val NUM_PAGES = 3
    }
}