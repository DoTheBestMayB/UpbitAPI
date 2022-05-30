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
import com.github.dodobest.upbitapi.model.DataFormat
import com.github.dodobest.upbitapi.model.MarketPlaceName
import com.github.dodobest.upbitapi.model.UpbitTickerDataForUI
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CoinListPageFragment : Fragment() {

    private lateinit var marketPlaceName: MarketPlaceName
    private lateinit var upbitAdapter: UpbitAdapter
    private lateinit var converter: DataFormat

    private var _binding: FragmentCoinListPageBinding? = null
    private val binding
        get() = _binding ?: throw IllegalArgumentException(getString(R.string.view_binding_is_null))

    private val viewModel: UpbitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataFormat()
        setRecyclerView()
        setLiveDataObserve()
    }

    override fun onStart() {
        super.onStart()

        loadContent()
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }

    private fun setDataFormat() {
        marketPlaceName = MarketPlaceName.from(requireArguments().getInt(MARKET_KEY_INDEX, -1))
            ?: MarketPlaceName.NEW

        converter = when (marketPlaceName) {
            MarketPlaceName.KRW -> DataFormat(
                aacTradeVolumeUnit = 1_000_000,
                aacTradeVolumeFormat = requireContext().getString(R.string.krw_aac_trade_volume_format)
            )
            MarketPlaceName.BTC, MarketPlaceName.USDT -> DataFormat(
                aacTradeVolumeUnit = 1,
                aacTradeVolumeFormat = "#,###.###",
            )
            MarketPlaceName.NEW -> {
                Timber.e(getString(R.string.no_exist_market))
                DataFormat(
                    aacTradeVolumeUnit = 1,
                    aacTradeVolumeFormat = "#,###.###",
                )
            }
        }
    }

    private fun setRecyclerView() {
        upbitAdapter = UpbitAdapter()
        binding.coinPriceRecyclerView.adapter = upbitAdapter

        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)

        AppCompatResources.getDrawable(requireContext(), R.drawable.divider)?.let {
            dividerItemDecoration.setDrawable(it)
            binding.coinPriceRecyclerView.addItemDecoration(dividerItemDecoration)
        } ?: Timber.e(getString(R.string.error_create_divider_drawable))
    }

    private fun setLiveDataObserve() {
        viewModel.tickers.observe(viewLifecycleOwner) {
            upbitAdapter.setResult(it.map { tickerData ->
                UpbitTickerDataForUI.convertAacTradePrice(
                    tickerData,
                    DataFormatHandler.formatAacTradePrice(
                        tickerData.aacTradePrice24h.toDouble(), converter
                    )
                )
            })
        }
    }

    private fun loadContent() {
        viewModel.getMarkets(marketPlaceName)
    }

    companion object {
        private const val MARKET_KEY_INDEX = "marketPlaceName"

        fun newInstance(position: Int): CoinListPageFragment {
            val coinListPageFragment = CoinListPageFragment()

            coinListPageFragment.arguments = Bundle().apply {
                putInt(MARKET_KEY_INDEX, position)
            }

            return coinListPageFragment
        }
    }
}