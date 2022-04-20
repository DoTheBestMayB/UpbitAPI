package com.github.dodobest.upbitapi

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber

class UpbitViewModel(
    private val getMarketsUseCase: GetMarketsUseCase,
    private val getTickerUseCase: GetTickerUseCase,
) : ViewModel() {
    private val _marketCoinNames = MutableLiveData<List<UpbitMarketData>>()
    val marketCoinNames: LiveData<List<UpbitMarketData>>
        get() = _marketCoinNames

    private val _tickers = MutableLiveData<Map<String, UpbitTickerData>>()
    val tickers: LiveData<Map<String, UpbitTickerData>>
        get() = _tickers

    private val _tickerSearchName = MutableLiveData<String>()
    val tickerSearchName: LiveData<String>
        get() = _tickerSearchName

    private val _errMessage = MutableLiveData<String>()
    val errMessage: LiveData<String>
        get() = _errMessage

    fun setTickerSearchName(inputSearchName: Editable) {
        _tickerSearchName.value = inputSearchName.toString()
    }

    fun getMarkets() {
        getMarketsUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ marketCoinNames ->
                _marketCoinNames.value = marketCoinNames
                marketCoinNames.map { marketCoinName ->
                    getTicker(marketCoinName.market)
                }
            }, {
                Timber.e(it.message ?: "")
                _errMessage.value = it.message
            })
    }

    fun getTicker(marketCodeName: String) {
        getTickerUseCase.execute(marketCodeName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ tickers ->
                tickers.map { ticker ->
                    _tickers.value = (_tickers.value ?: mapOf()) +
                            mapOf(marketCodeName to ticker)
                }
            }, {
                Timber.e(it.message ?: "")
                _errMessage.value = it.message
            })
    }
}