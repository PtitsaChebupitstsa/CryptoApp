package com.example.cryptoapp.data.di

import com.example.cryptoapp.data.di.qualifiers.WorkerKey
import com.example.cryptoapp.data.workers.ChildWorkerFactory
import com.example.cryptoapp.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {
    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory (worker: RefreshDataWorker.Factory):ChildWorkerFactory
}