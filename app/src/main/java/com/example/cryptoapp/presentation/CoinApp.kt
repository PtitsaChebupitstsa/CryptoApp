package com.example.cryptoapp.presentation

import android.app.Application
import com.example.cryptoapp.data.di.DaggerApplicationComponent

class CoinApp:Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}