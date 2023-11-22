package com.example.cryptoapp.domain

import androidx.lifecycle.LiveData

interface CoinInfoRepository {
  fun getCoinItem(fromSymbol:String): CoinInfo
    fun getCoinItemList(): LiveData<List<CoinInfo>>
}

