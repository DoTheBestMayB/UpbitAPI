package com.github.dodobest.upbitapi

import android.text.Editable
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

    private val _tickerData = MutableLiveData<Map<String, UpbitTickerData>>()
    val tickerData: LiveData<Map<String, UpbitTickerData>>
        get() = _tickerData

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
                _tickerData.value = _tickerData.value ?: mapOf<String, UpbitTickerData>() +
                        mapOf(market to upbitTickerData)
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