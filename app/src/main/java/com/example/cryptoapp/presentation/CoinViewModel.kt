package com.example.cryptoapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    private val db = AppDatabase.getInstance(application)

    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun getDetailInfo(fSym: String): LiveData<CoinInfoDto> {
        Log.d("getDetailInfo", fSym)
        Log.d("getDetailInfo_return", db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym).toString())
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)

    }


    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 10)
            .map { it.names?.map { it.coinNameDto?.name }?.joinToString(",").toString() }
            //joinToString коллекцию строк преврашает в одну стоку и разделяет их сепораторо в нашем случае  ","
            // map преобразуем строку в поле datum и далее мы получаем из датума коин инфо и далее мы получаем имя
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            // flatMap  если мы хотим выпонить еще какую то загрузку в этом чейне (в нашем случаем мы загружаем getFullPriceList
            .map { getPriceListRawData(it) }.subscribeOn(Schedulers.io())
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()//постоянно повторяет загрузку если все прошло успешно
            .retry()//постоянно повторяет загрузку если все прошло с ошибкой(не успешно) и repeat перестал повторять загрузку
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinPriceInfoDao().insertPriceList(it)
                Log.d("TEST_OF_LOADING_DATA", "Success:$it")
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure:${it.message}")
            })

        compositeDisposable.add(disposable)
    }


    private fun getPriceListRawData(coinInfoJsonContainerDto: CoinInfoJsonContainerDto): List<CoinInfoDto> {//преврашаем обьект преобразованный flatMap лист данных
        Log.d("getPriceListRawData(coinPriceInfoRawData)", coinInfoJsonContainerDto.toString())

        val result = ArrayList<CoinInfoDto>()
        val jsonObject = coinInfoJsonContainerDto.json ?: return result
        val coinKeySet = jsonObject.keySet()//получаем набор ключей вложеных в него

        Log.d("getPriceListRawData(coinKeySet)", coinKeySet.toString())

        for (coinKey in coinKeySet) {
            val currencyJson =
                jsonObject.getAsJsonObject(coinKey)// результат {"BTC":{"USD":{"TYPE":"5","MARKET":"CCCAGG","FROMSYMBOL":"BTC","TOSYMBOL":"USD","FLAGS":"1","LASTMARKET":"CCCAGG","MEDIAN" и тд в одном бьекте

            Log.d("getPriceListRawData(currencyJson)", currencyJson.toString())

            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo =
                    Gson().fromJson( // что бы из json получить нужный нам отьект , CoinPriceInfo::class.java класс в который мы хотим сконвертировать
                        currencyJson.getAsJsonObject(currencyKey), CoinInfoDto::class.java
                    )
                result.add(priceInfo)// результат "TYPE":"5","MARKET":"CCCAGG","FROMSYMBOL":"BTC","TOSYMBOL":"USD","FLAGS":"1","LASTMARKET":"CCCAGG","M
            }

        }
        Log.d("getPriceListRawData(result)", result.toString())
        return result
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

