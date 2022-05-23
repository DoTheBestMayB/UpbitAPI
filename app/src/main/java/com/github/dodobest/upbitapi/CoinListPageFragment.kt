package com.github.dodobest.upbitapi

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.dodobest.upbitapi.databinding.FragmentCoinListPageBinding
import com.github.dodobest.upbitapi.di.UpbitViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoinListPageFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var marketPlaceName: String
    @Inject lateinit var upbitViewModelFactory: UpbitViewModelFactory

    private var _binding : FragmentCoinListPageBinding? = null
    private val binding get() = _binding!!

    private val upbitAdapter: UpbitAdapter = UpbitAdapter()
    private lateinit var viewModel: UpbitViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(Constant.ARGUMENT_OF_COIN_LIST_FRAGMENT) }?.apply {
            marketPlaceName = getString(Constant.ARGUMENT_OF_COIN_LIST_FRAGMENT).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListPageBinding.inflate(inflater, container, false)
        setViewModel()
        setRecyclerView()
        setLiveDataObserve()
        loadInitialContent()
        return binding.root
    }

    private fun setViewModel() {
        viewModel = upbitViewModelFactory.create(marketPlaceName)
    }

    private fun loadInitialContent() {
        viewModel.getMarkets()
    }

    private fun setLiveDataObserve() {
        viewModel.tickers.observe(viewLifecycleOwner) {
            upbitAdapter.setResult(it)
        }
    }

    private fun setRecyclerView() {
        binding.coinPriceRecyclerView.adapter = upbitAdapter

        val divider = DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL)
        AppCompatResources.getDrawable(mainActivity, R.drawable.divider)?.let {
            divider.setDrawable(it)
        } ?: throw IllegalArgumentException()
        binding.coinPriceRecyclerView.addItemDecoration(divider)
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}