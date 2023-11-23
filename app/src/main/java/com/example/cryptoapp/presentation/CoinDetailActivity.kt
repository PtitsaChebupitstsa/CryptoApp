package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol!!).observe(this) {
            Log.d("DETAIL_INFO", it.toString())
            with(binding) {
                tvPrice.text = it.price.toString()
                tvMinPrice.text = it.lowDay.toString()
                tvMaxPrice.text = it.highDay.toString()
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = it.lastUpdate
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Picasso.get().load(it.imageURL).into(ivLogoCoin)
            }
        }
    }

    companion object {
        //передача между активити
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""
        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }

    private fun initView() {

    }
}