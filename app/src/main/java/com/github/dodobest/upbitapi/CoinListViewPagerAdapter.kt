package com.github.dodobest.upbitapi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.dodobest.upbitapi.model.MarketPlaceName

class CoinListViewPagerAdapter(
    fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = MarketPlaceName.values().size-1

    override fun createFragment(position: Int): Fragment {
        return CoinListPageFragment.newInstance(position)
    }
}