package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso


class CoinInfoAdapter (private val context:Context): RecyclerView.Adapter<CoinInfoViewHolder>() {
    var coinInfoList:List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return CoinInfoViewHolder(binding)

    }

    override fun getItemCount() = coinInfoList.size


    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder.binding) {
            val symbolsTemplate = context.resources.getString(R.string.symbols_template)
            val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
            tvSymbols.text = String.format(symbolsTemplate,coin.fromSymbol,coin.fromSymbol)
            tvPrice.text = coin.price.toString()
            tvTimeLastUpdate.text = String.format(lastUpdateTemplate, coin.lastUpdate)
            Picasso
                .get()
                .load(coin.imageURL)
                .into(ivLogoCoin)
            root.setOnClickListener{
                onCoinClickListener?.onCoinClick(coin)
        }
        }
    }
    interface  OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }
}