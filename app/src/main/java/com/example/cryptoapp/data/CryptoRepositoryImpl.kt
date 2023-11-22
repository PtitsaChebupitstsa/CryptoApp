package com.example.cryptoapp.data

import androidx.lifecycle.LiveData
import com.example.cryptoapp.domain.CoinInfoRepository
import com.example.cryptoapp.domain.CoinInfo

class CryptoRepositoryImpl:CoinInfoRepository {
    override suspend fun getCoinItem(coinItemId: Int): CoinInfo {
        TODO("Not yet implemented")
    }

    override fun getCoinItemList(): LiveData<List<CoinInfo>> {
        TODO("Not yet implemented")
    }
}