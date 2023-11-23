package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso


class CoinInfoAdapter (private val context:Context): RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    var coinInfoList:List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener?=null
    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin = itemView.findViewById<ImageView>(R.id.ivLogoCoin)
        val tvSymbols = itemView.findViewById<TextView>(R.id.tvSymbols)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        val tvLastUpdate = itemView.findViewById<TextView>(R.id.tvTimeLastUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)

    }

    override fun getItemCount() = coinInfoList.size


    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
        with(holder) {
//            tvSymbols.text = coin.fromSymbol + " / " + coin.toSymbol
            tvSymbols.text = String.format(symbolsTemplate,coin.fromSymbol,coin.fromSymbol)
            tvPrice.text = coin.price.toString()
//            tvLastUpdate.text = "Время последнего обновления: ${coin.getFormattedTime()}"
            tvLastUpdate.text = String.format(lastUpdateTemplate, convertTimestampToTime(coin.lastUpdate))

            Picasso
                .get()
                .load(ApiFactory.BASE_IMAGE_URL +coin.imageURL)
                .into(ivLogoCoin)//пикассо подключаем и скачиваем


            itemView.setOnClickListener{
                onCoinClickListener?.onCoinClick(coin)
        }

        }
    }
//слушатель нажатия
    interface  OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }
}