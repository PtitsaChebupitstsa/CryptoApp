package com.example.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.api.ApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

private lateinit var viewModel:CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel= ViewModelProvider(this)[CoinViewModel::class.java]

        viewModel.priceList.observe(this, Observer {
            Log.d("TEST_OF_LOADING_DATA_MAIN_ACT","Success in activity: $it")
        })
        viewModel.getDetailInfo("BTC").observe(this, Observer {
            Log.d("TEST_OF_LOADING_DATA_MAIN_ACT","Success in getDetailInfo: $it")
        })

    }

}