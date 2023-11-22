package com.example.cryptoapp.domain

class GetCoinInfoUseCase(private val repository: CoinInfoRepository) {

    operator fun invoke(fromSymbol:String)= repository.getCoinItem(fromSymbol)
}