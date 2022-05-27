package com.github.dodobest.upbitapi

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
import timber.log.Timber

@AndroidEntryPoint
class CoinListPageFragment : Fragment() {

    private lateinit var marketPlaceName: MarketPlaceName
    private lateinit var upbitAdapter: UpbitAdapter

    private var _binding: FragmentCoinListPageBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException(getString(R.string.view_binding_is_null))

    private val viewModel: UpbitViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(Constant.ARGUMENT_MARKET_INDEX) }?.apply {
            MarketPlaceName.from(getInt(Constant.ARGUMENT_MARKET_INDEX))?.let {
                marketPlaceName = it
            } ?: throw IllegalArgumentException(getString(R.string.no_exist_market))
        } ?: throw IllegalArgumentException(getString(R.string.no_exist_market))
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
        viewModel.getMarkets(marketPlaceName)
    }

    private fun setLiveDataObserve() {
        viewModel.tickers.observe(viewLifecycleOwner) {
            upbitAdapter.setResult(it)
        }
    }

    private fun setRecyclerView() {
        upbitAdapter = UpbitAdapter(marketPlaceName)
        binding.coinPriceRecyclerView.adapter = upbitAdapter

        context?.let { context ->
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            ).let { dividerItemDecoration ->
                AppCompatResources.getDrawable(context, R.drawable.divider)?.let {
                    dividerItemDecoration.setDrawable(it)
                } ?: run {
                    Timber.e(getString(R.string.error_create_divider_drawable))
                    return
                }

                binding.coinPriceRecyclerView.addItemDecoration(dividerItemDecoration)
            }
        } ?: throw IllegalArgumentException(getString(R.string.no_exist_context))
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}