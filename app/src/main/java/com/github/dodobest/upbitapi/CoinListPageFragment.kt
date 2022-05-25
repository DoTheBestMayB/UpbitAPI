package com.github.dodobest.upbitapi

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.dodobest.upbitapi.databinding.FragmentCoinListPageBinding
import com.github.dodobest.upbitapi.model.MarketPlaceName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListPageFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var marketPlaceName: MarketPlaceName
    private lateinit var upbitAdapter: UpbitAdapter

    private var _binding: FragmentCoinListPageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UpbitViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(Constant.ARGUMENT_OF_COIN_LIST_FRAGMENT) }?.apply {
            MarketPlaceName.from(getInt(Constant.ARGUMENT_OF_COIN_LIST_FRAGMENT))?.let {
                marketPlaceName = it
            } ?: throw IllegalArgumentException(Constant.NO_EXIST_MARKET)
        } ?: throw IllegalArgumentException(Constant.NO_EXIST_MARKET)
        setRecyclerView()
        setLiveDataObserve()
        loadInitialContent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun loadInitialContent() {
        viewModel.getMarkets(marketPlaceName.toString())
    }

    private fun setLiveDataObserve() {
        viewModel.tickers.observe(viewLifecycleOwner) {
            upbitAdapter.setResult(it)
        }
    }

    private fun setRecyclerView() {
        upbitAdapter = UpbitAdapter(marketPlaceName)
        binding.coinPriceRecyclerView.adapter = upbitAdapter

        val divider = DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL)
        AppCompatResources.getDrawable(mainActivity, R.drawable.divider)?.let {
            divider.setDrawable(it)
        } ?: throw IllegalArgumentException(Constant.NO_EXIST_MARKET)
        binding.coinPriceRecyclerView.addItemDecoration(divider)
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}