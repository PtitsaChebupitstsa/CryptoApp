package com.example.cryptoapp.data.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.workers.RefreshDataWorker
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.domain.CoinRepository
import com.example.cryptoapp.mapper.CoinMapper
import javax.inject.Inject


class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val mapper: CoinMapper,
    private val coinInfoDao:CoinInfoDao
) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        val coinInfoList = coinInfoDao.getPriceList()
        return coinInfoList.map {
            mapper.mapDbModelListToEntityList(it)
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        val coinInfo = coinInfoDao.getPriceInfoAboutCoin(fromSymbol)
        return coinInfo.map { mapper.mapDbModelToEntity(it) }
    }


    override fun loadData() {

val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.WORKER_NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}


