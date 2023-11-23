package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)?:EMPTY_SYMBOL
        val tvPrice = findViewById<TextView>(R.id.tvPrice)
        val tvMinPrice = findViewById<TextView>(R.id.tvMinPrice)
        val tvMaxPrice = findViewById<TextView>(R.id.tvMaxPrice)
        val tvLastMarket = findViewById<TextView>(R.id.tvLastMarket)
        val tvLastUpdate = findViewById<TextView>(R.id.tvLastUpdate)
        val tvFromSymbol = findViewById<TextView>(R.id.tvFromSymbol)
        val tvToSymbol = findViewById<TextView>(R.id.tvToSymbol)
        val ivLogoCoin = findViewById<ImageView>(R.id.ivLogoCoin)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol!!).observe(this) {
            Log.d("DETAIL_INFO", it.toString())
            tvPrice.text = it.price.toString()
            tvMinPrice.text = it.lowDay.toString()
            tvMaxPrice.text = it.highDay.toString()
            tvLastMarket.text = it.lastMarket
            tvLastUpdate.text = convertTimestampToTime(it.lastUpdate)
            tvFromSymbol.text = it.fromSymbol
            tvToSymbol.text = it.toSymbol
            Picasso.get().load(ApiFactory.BASE_IMAGE_URL +it.imageURL).into(ivLogoCoin)
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