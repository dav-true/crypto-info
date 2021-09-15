package com.plcoding.cryptoinfo.data.remote

import com.plcoding.cryptoinfo.common.Constants
import com.plcoding.cryptoinfo.data.remote.dto.CoinDetailDto
import com.plcoding.cryptoinfo.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinsApi {

    @GET(Constants.coinsUrl)
    suspend fun getCoins(): List<CoinDto>

    @GET(Constants.coinDetailsUrl)
    suspend fun getCoinDetail(@Path(Constants.PARAM_COIN_ID) coinId: String): CoinDetailDto

}