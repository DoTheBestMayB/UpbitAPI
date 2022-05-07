package com.github.dodobest.upbitapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerDataWithKoreanName
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
    private val marketToKoreanName: HashMap<String, String> = hashMapOf()

    private val _marketCoinData = MutableLiveData<List<UpbitMarketData>>()
    val marketCoinData: LiveData<List<UpbitMarketData>>
        get() = _marketCoinData

    private var tickerQuery: String = ""

    private val _tickers = MutableLiveData<List<UpbitTickerDataWithKoreanName>>()
    val tickers: LiveData<List<UpbitTickerDataWithKoreanName>>
        get() = _tickers

    private fun extractTickerQuery() {
        val coinName: ArrayList<String> = arrayListOf()

        for (marketName in marketToKoreanName.keys) {
            if (marketName.contains("KRW-")) {
                coinName.add(marketName)
            }
        }

        tickerQuery = coinName.toString().replace(" ", "")
        tickerQuery = tickerQuery.slice(IntRange(1, tickerQuery.length - 2))
    }

    fun getMarkets() {
        getMarketsUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ marketCoinNames ->
                _marketCoinData.value = marketCoinNames
                setMarketToKoreanName(marketCoinNames)
                extractTickerQuery()
                getTicker(tickerQuery)
            }, {
                Timber.e(it.message ?: "")
            })
    }

    private fun setMarketToKoreanName(marketCoinNames: List<UpbitMarketData>) {
        marketToKoreanName.clear()
        for (marketCoinName in marketCoinNames) {
            marketToKoreanName[marketCoinName.market] = marketCoinName.koreanName
        }
    }

    fun getTicker(query: String) {
        if (query == "") {
            return
        }
        getTickerUseCase.execute(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ upbitTickerDataSet ->
                _tickers.value = upbitTickerDataSet.map { upbitTickerData ->
                    upbitTickerData.addKoreanName(marketToKoreanName[upbitTickerData.market]!!)
                }
            }, {
                Timber.e(it.message ?: "")
            })
    }
}