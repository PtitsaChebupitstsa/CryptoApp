package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.example.cryptoapp.data.model.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val rvCoinPriceList = findViewById<RecyclerView>(R.id.rvCoinPriceList)
        val adapter = CoinInfoAdapter(this)
        rvCoinPriceList.adapter = adapter
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                //старт новой активити
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)

            }

        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        // Наблюдатель за изменениями в списке монет
        viewModel.priceList.observe(this, Observer { coinList ->
            // Обновляем список монет в адаптере, когда данные изменяются
            adapter.coinInfoList = coinList
        })

        // Вызывайте метод для загрузки данных из ViewModel (например, viewModel.loadCoinList())
    }

}