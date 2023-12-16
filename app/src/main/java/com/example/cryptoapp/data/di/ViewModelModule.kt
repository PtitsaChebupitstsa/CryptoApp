package com.example.cryptoapp.data.di

import androidx.lifecycle.ViewModel
import com.example.cryptoapp.data.di.qualifiers.ViewModelKey
import com.example.cryptoapp.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindCoinViewModel (impl:CoinViewModel):ViewModel
}