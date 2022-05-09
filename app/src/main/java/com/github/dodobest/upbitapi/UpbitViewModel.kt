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

    private val _tickers = MutableLiveData<List<UpbitTickerDataWithKoreanName>>()
    val tickers: LiveData<List<UpbitTickerDataWithKoreanName>>
        get() = _tickers

    fun getMarkets() {
        getMarketsUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ marketCoinNames ->
                setMarketToKoreanName(marketCoinNames)
                getTicker(extractTickerQuery())
            }, {
                Timber.e(it.message ?: "")
            })
    }


    fun getTicker(query: String) {
        getTickerUseCase.execute(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ upbitTickerDataSet ->
                _tickers.value = upbitTickerDataSet.map { upbitTickerData ->
                    marketToKoreanName[upbitTickerData.market]?.let { koreanName ->
                        upbitTickerData.addKoreanName(koreanName)
                    } ?: upbitTickerData.addKoreanName(NO_NAME_NONE)
                }
            }, {
                Timber.e(it.message ?: "")
            })
    }

    private fun extractTickerQuery(): String {
        val coinName: ArrayList<String> = arrayListOf()

        for (marketName in marketToKoreanName.keys) {
            if (marketName.contains("KRW-")) {
                coinName.add(marketName)
            }
        }
        val tickerQuery = coinName.toString().replace(" ", "")
        return tickerQuery.slice(IntRange(1, tickerQuery.length - 2))
    }

    private fun setMarketToKoreanName(marketCoinNames: List<UpbitMarketData>) {
        marketToKoreanName.clear()
        for (marketCoinName in marketCoinNames) {
            marketToKoreanName[marketCoinName.market] = marketCoinName.koreanName
        }
    }

    companion object {
        private const val NO_NAME_NONE = "None"
    }
}