package com.github.dodobest.upbitapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.upbitapi.model.Coin
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

    fun getMarkets() {
        getMarketsUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getTicker(it)
            }, {
                Timber.e(it.message ?: "")
            })
    }


    fun getTicker(upbitMarketDataSet: List<UpbitMarketData>) {
        getTickerUseCase.execute(extractTickerQuery(upbitMarketDataSet))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ upbitTickerDataSet ->
                _tickers.value = upbitTickerDataSet.map { upbitTickerData ->
                    val koreanName = upbitMarketDataSet.find { upbitMarketData ->
                        upbitMarketData.market == upbitTickerData.market
                    }?.koreanName ?: Coin.NO_EXIST.koreanName
                    UpbitTickerDataWithKoreanName.fromUpbitTickerData(upbitTickerData, koreanName)
                }
            }, {
                Timber.e(it.message ?: "")
            })
    }

    private fun extractTickerQuery(upbitMarketDataSet: List<UpbitMarketData>): String {
        val coinName: ArrayList<String> = arrayListOf()

        upbitMarketDataSet.forEach { upbitMarketData ->
            if (upbitMarketData.market.contains("USDT-")) {
                coinName.add(upbitMarketData.market)
            }
        }

        return coinName.joinToString().replace(" ", "")
    }
}