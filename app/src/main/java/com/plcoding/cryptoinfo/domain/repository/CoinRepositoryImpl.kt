package com.plcoding.cryptoinfo.domain.repository

import com.plcoding.cryptoinfo.data.remote.CoinsApi
import com.plcoding.cryptoinfo.data.remote.dto.CoinDetailDto
import com.plcoding.cryptoinfo.data.remote.dto.CoinDto
import javax.inject.Inject


class CoinRepositoryImpl @Inject constructor(
    private val apiService: CoinsApi
) : CoinRepository {

    override suspend fun getCoins() = apiService.getCoins()

    override suspend fun getCoinDetails(coinId: String) = apiService.getCoinDetail(coinId = coinId)
}