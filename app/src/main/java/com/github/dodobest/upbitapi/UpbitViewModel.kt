package com.github.dodobest.upbitapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dodobest.domain.model.UpbitMarketData
import com.github.dodobest.domain.model.UpbitTickerData
import com.github.dodobest.domain.usecase.GetMarketsUseCase
import com.github.dodobest.domain.usecase.GetTickerUseCase
import com.github.dodobest.upbitapi.model.DataFormat
import com.github.dodobest.upbitapi.model.MarketPlaceName
import com.github.dodobest.upbitapi.model.UpbitTickerDataForUI
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UpbitViewModel @Inject constructor(
    private val getMarketsUseCase: GetMarketsUseCase,
    private val getTickerUseCase: GetTickerUseCase,
) : ViewModel() {

    private val _tickers = MutableLiveData<List<UpbitTickerDataForUI>>()
    val tickers: LiveData<List<UpbitTickerDataForUI>>
        get() = _tickers

    private val coinHashMap: HashMap<String, String> = hashMapOf()

    private var krwDataFormat = DataFormat(
        "#,###.##%", "#,###.########"
    )

    private val btcDataFormat = DataFormat(
        "#,###.##%", "#,###.########",
    )
    private val usdtDataFormat = DataFormat(
        "#,###.##%", "#,###.###",
    )

    private val dataFormat = mapOf(
        "KRW" to krwDataFormat, "BTC" to btcDataFormat, "USDT" to usdtDataFormat
    )

    fun getMarkets(marketPlaceName: MarketPlaceName) {
        getMarketsUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                extractCoinName(it)
                getTicker(it, marketPlaceName)
            }, {
                Timber.e(it.message ?: "")
            })
    }

    fun extractCoinName(upbitMarketDataSet: List<UpbitMarketData>) {
        upbitMarketDataSet.forEach { upbitMarketData ->
            coinHashMap[upbitMarketData.market] = upbitMarketData.koreanName
        }
    }


    fun getTicker(upbitMarketDataSet: List<UpbitMarketData>, marketPlaceName: MarketPlaceName) {
        getTickerUseCase.execute(extractTickerQuery(upbitMarketDataSet, marketPlaceName))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ upbitTickerDataSet ->
                _tickers.value = upbitTickerDataSet.map { upbitTickerData ->
                    convertTicker(upbitTickerData, marketPlaceName)
                }
            }, {
                Timber.e(it.message ?: "")
            })
    }

    private fun convertTicker(
        upbitTickerData: UpbitTickerData,
        marketPlaceName: MarketPlaceName
    ): UpbitTickerDataForUI {
        val converter = dataFormat[marketPlaceName.toString()] ?: throw IllegalArgumentException()

        return UpbitTickerDataForUI.fromUpbitTickerData(
            upbitTickerData,
            coinHashMap[upbitTickerData.market] ?: NO_EXIST_COIN,
            DataFormatHandler.formatTradePrice(upbitTickerData.tradePrice, converter),
            DataFormatHandler.formatChangeRate(upbitTickerData.signedChangeRate, converter),
        )
    }

    private fun extractTickerQuery(
        upbitMarketDataSet: List<UpbitMarketData>,
        marketPlaceName: MarketPlaceName
    ): String {
        val coinName: ArrayList<String> = arrayListOf()

        upbitMarketDataSet.forEach { upbitMarketData ->
            if (upbitMarketData.market.contains("$marketPlaceName-")) {
                coinName.add(upbitMarketData.market)
            }
        }

        return coinName.joinToString().replace(" ", "")
    }

    companion object {
        private const val NO_EXIST_COIN = "등록되지 않은 코인"
    }
}