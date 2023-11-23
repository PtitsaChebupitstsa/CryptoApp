package com.example.cryptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    var fromSymbol: String,
    val toSymbol: String?,
    val lastMarket: String?,
    val price: Double?,
    val lastUpdate: Long?,
    val highDay: Double?,
    val lowDay: Double?,
    val imageURL: String?

)

