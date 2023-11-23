package com.example.cryptoapp.data.network

import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoapp.data.network.model.CoinNamesListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
   suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey:String = API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit:Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym:String = CURRENCY,
    ):CoinNamesListDto
@GET("pricemultifull")
   suspend fun getFullPriceList(
    @Query(QUERY_PARAM_API_KEY) apiKey:String = API_KEY,
    @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms:String,
    @Query(QUERY_PARAM_TO_SYMBOLS) tSyms:String = CURRENCY
    ):CoinInfoJsonContainerDto

    companion object{
        private const val API_KEY="7a36b62e52b8832dcdbfd35cf0bd764d383e8694f23b98a6f484162c8f6491d6"
        private const val QUERY_PARAM_API_KEY="api_key"
        private const val QUERY_PARAM_LIMIT="limit"
        private const val QUERY_PARAM_TO_SYMBOL="tsym"
        private const val QUERY_PARAM_TO_SYMBOLS="tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS="fsyms"
        private const val CURRENCY="USD"
    }

}