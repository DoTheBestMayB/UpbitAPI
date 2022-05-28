package com.github.dodobest.upbitapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    private val viewModel: UpbitViewModel by activityViewModels()

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
        loadInitialContent()
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }

    private fun setDataFormat() {
        marketPlaceName = MarketPlaceName.from(requireArguments().getInt(MARKET_KEY_INDEX, -1))
            ?: MarketPlaceName.NEW

        converter = when (marketPlaceName.toString()) {
            "KRW" -> DataFormat(
                aacTradeVolumeUnit = 1_000_000,
                aacTradeVolumeFormat = requireContext().getString(R.string.krw_aac_trade_volume_format)
            )
            "BTC", "USDT" -> DataFormat(
                aacTradeVolumeUnit = 1,
                aacTradeVolumeFormat = "#,###.###",
            )
            else -> {
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
        } ?: Timber.e(getString(R.string.no_exist_context))
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

    private fun loadInitialContent() {
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