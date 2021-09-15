package com.plcoding.cryptoinfo.domain.use_cases.get_coins

import com.plcoding.cryptoinfo.common.NetworkResult
import com.plcoding.cryptoinfo.data.remote.dto.CoinDto
import com.plcoding.cryptoinfo.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<NetworkResult<List<CoinDto>>> = flow {
        try {
            emit(NetworkResult.Loading())
            val coins = repository.getCoins()
            emit(NetworkResult.Success(coins))
        } catch (e: HttpException) {
            emit(NetworkResult.Failure(e.localizedMessage ?: "Unexpected error occurred"))
        } catch (e: IOException) {
            emit(NetworkResult.Failure(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}