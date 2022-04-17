package com.github.dodobest.upbitapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.upbitapi.scheduler.SchedulerProvider
import io.reactivex.rxjava3.core.Single

class UpbitViewModel(
    private val getMarketsUseCase: GetMarketsUseCase,
    private val getTickerUseCase: GetTickerUseCase,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private val _marketData = MutableLiveData<List<UpbitMarketData>>()
    val marketData: LiveData<List<UpbitMarketData>>
        get() = _marketData

    private val _tickerData = MutableLiveData<MutableMap<String, UpbitTickerData>>()
    val tickerData: LiveData<MutableMap<String, UpbitTickerData>>
        get() = _tickerData

    private val _errMessage = MutableLiveData<String>()
    val errMessage: LiveData<String>
        get() = _errMessage

    fun getMarkets() {
        processSingleData(getMarketsUseCase.execute(), { upbitMarketDataList ->
            _marketData.value = upbitMarketDataList
            upbitMarketDataList.map { upbitMarketData ->
                getTicker(upbitMarketData.englishName)
            }
        }, {
            Log.d(TAG, it.message ?: "")
            _errMessage.value = it.message
        })
    }

    fun getTicker(market: String) {
        processSingleData(getTickerUseCase.execute(market), { upbitTickerDataList ->
            upbitTickerDataList.map { upbitTickerData ->
                if (_tickerData.value != null) {
                    _tickerData.value!![market] = upbitTickerData
                }
                _tickerData.value = mutableMapOf(market to upbitTickerData)
            }
        }, {
            Log.d(TAG, it.message ?: "")
            _errMessage.value = it.message
        })
    }

    private fun <T> processSingleData(
        singleData: Single<List<T>>,
        onSuccess: (List<T>) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        singleData.subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(onSuccess, onFailure)
    }

    companion object {
        private const val TAG = "UpbitViewModel"
    }
}