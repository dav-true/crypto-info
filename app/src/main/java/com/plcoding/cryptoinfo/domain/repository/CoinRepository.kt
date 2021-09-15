package com.plcoding.cryptoinfo.domain.repository

import com.plcoding.cryptoinfo.data.remote.dto.CoinDetailDto
import com.plcoding.cryptoinfo.data.remote.dto.CoinDto
import dagger.Provides
import javax.inject.Singleton

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinDetails(coinId: String): CoinDetailDto

}