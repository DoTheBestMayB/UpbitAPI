package com.github.dodobest.upbitapi

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UpbitViewModel @Inject constructor(
    private val getMarketsUseCase: GetMarketsUseCase,
    private val getTickerUseCase: GetTickerUseCase,
) : ViewModel() {
    private val _marketCoinData = MutableLiveData<List<UpbitMarketData>>()
    val marketCoinData: LiveData<List<UpbitMarketData>>
        get() = _marketCoinData

    private var tickerQuery: String = ""

    private val _tickers = MutableLiveData<List<UpbitTickerData>>()
    val tickers: LiveData<List<UpbitTickerData>>
        get() = _tickers

    private val _tickerSearchName = MutableLiveData<String>()
    val tickerSearchName: LiveData<String>
        get() = _tickerSearchName

    private val _errMessage = MutableLiveData<String>()
    val errMessage: LiveData<String>
        get() = _errMessage

    private fun extractTickerQuery() {
        val coinName: ArrayList<String> = arrayListOf()

        _marketCoinData.value?.let { upbitMarkets ->
            upbitMarkets.map { upbitMarketData ->
                coinName.add(upbitMarketData.market)
            }
        }
        tickerQuery = coinName.toString().replace(" ", "")
        tickerQuery = tickerQuery.slice(IntRange(1, tickerQuery.length-2))
    }

    fun setTickerSearchName(inputSearchName: Editable) {
        _tickerSearchName.value = inputSearchName.toString()
    }

    fun getMarkets() {
        getMarketsUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ marketCoinNames ->
                _marketCoinData.value = marketCoinNames
                extractTickerQuery()
                getTicker(tickerQuery)
            }, {
                Timber.e(it.message ?: "")
                _errMessage.value = it.message
            })
    }

    fun getTicker(query: String) {
        if (query == "") {
            return
        }
        getTickerUseCase.execute(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _tickers.value = it
            }, {
                Timber.e(it.message ?: "")
                _errMessage.value = it.message
            })
    }
}