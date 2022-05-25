package com.github.dodobest.upbitapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CoinListViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        val fragment = CoinListPageFragment()
        fragment.arguments = Bundle().apply {
            putInt(Constant.ARGUMENT_OF_COIN_LIST_FRAGMENT, position)
        }
        return fragment
    }

    companion object {
        private const val NUM_PAGES = 3
    }
}