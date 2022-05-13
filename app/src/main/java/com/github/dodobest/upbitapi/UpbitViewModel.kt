package com.github.dodobest.upbitapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.upbitapi.model.UpbitTickerDataWithKoreanName
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UpbitViewModel @Inject constructor(
    private val getMarketsUseCase: GetMarketsUseCase,
    private val getTickerUseCase: GetTickerUseCase,
) : ViewModel() {

    private val _tickers = MutableLiveData<List<UpbitTickerDataWithKoreanName>>()
    val tickers: LiveData<List<UpbitTickerDataWithKoreanName>>
        get() = _tickers

    private val coinHashMap: HashMap<String, String> = hashMapOf()

    fun getMarkets() {
        getMarketsUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                extractCoinName(it)
                getTicker(it)
            }, {
                Timber.e(it.message ?: "")
            })
    }

    private fun extractCoinName(upbitMarketDataSet: List<UpbitMarketData>) {
        upbitMarketDataSet.forEach { upbitMarketData ->
            coinHashMap[upbitMarketData.market] = upbitMarketData.koreanName
        }
    }


    fun getTicker(upbitMarketDataSet: List<UpbitMarketData>) {
        getTickerUseCase.execute(extractTickerQuery(upbitMarketDataSet))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ upbitTickerDataSet ->
                _tickers.value = upbitTickerDataSet.map { upbitTickerData ->
                    UpbitTickerDataWithKoreanName.fromUpbitTickerData(
                        upbitTickerData,
                        coinHashMap[upbitTickerData.market] ?: NO_EXIST,
                    )
                }
            }, {
                Timber.e(it.message ?: "")
            })
    }

    private fun extractTickerQuery(upbitMarketDataSet: List<UpbitMarketData>): String {
        val coinName: ArrayList<String> = arrayListOf()

        upbitMarketDataSet.forEach { upbitMarketData ->
            if (upbitMarketData.market.contains("KRW-")) {
                coinName.add(upbitMarketData.market)
            }
        }

        return coinName.joinToString().replace(" ", "")
    }

    companion object {
        private const val NO_EXIST = "등록되지 않은 코인"
    }
}