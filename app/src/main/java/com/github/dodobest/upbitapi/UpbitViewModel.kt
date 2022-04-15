package com.github.dodobest.upbitapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UpbitViewModel(
    private val getMarketsUseCase: GetMarketsUseCase,
    private val getTickerUseCase: GetTickerUseCase,
) : ViewModel() {
    private val _coinNameData = MutableLiveData<List<UpbitMarketData>>()
    val coinNameData: LiveData<List<UpbitMarketData>>
        get() = _coinNameData

    private val _coinPriceData = MutableLiveData<MutableMap<String, UpbitTickerData>>()
    val coinPriceData: LiveData<MutableMap<String, UpbitTickerData>>
        get() = _coinPriceData

    private val _errMessage = MutableLiveData<String>()
    val errMessage: LiveData<String>
        get() = _errMessage

    fun getMarkets() {
        processSingleData(getMarketsUseCase.execute(), { upbitMarketDataList ->
            _coinNameData.value = upbitMarketDataList
            upbitMarketDataList.map { upbitMarketData ->
                getTicker(upbitMarketData.englishName)
            }
        }, {
            Log.d(TAG, it.message ?: "")
            _errMessage.value = it.message
        })
    }

    fun getTicker(coinName: String) {
        processSingleData(getTickerUseCase.execute(coinName), { upbitTickerDataList ->
            upbitTickerDataList.map { upbitTickerData ->
                if (_coinPriceData.value != null) {
                    _coinPriceData.value!![coinName] = upbitTickerData
                }
                _coinPriceData.value = mutableMapOf(coinName to upbitTickerData)
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
        singleData.subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.io())
            .subscribe(onSuccess, onFailure)
    }

    companion object {
        private const val TAG = "UpbitViewModel"
    }
}