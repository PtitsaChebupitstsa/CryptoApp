package com.example.cryptoapp.domain

class GetCoinInfoListUseCase(private val repository: CoinInfoRepository) {
operator fun invoke()=repository.getCoinItemList()
}