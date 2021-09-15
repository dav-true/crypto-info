package com.plcoding.cryptoinfo.domain.use_cases.get_coin_details

import com.plcoding.cryptoinfo.common.NetworkResult
import com.plcoding.cryptoinfo.data.remote.dto.CoinDetailDto
import com.plcoding.cryptoinfo.data.remote.dto.CoinDto
import com.plcoding.cryptoinfo.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<NetworkResult<CoinDetailDto>> = flow {
        try {
            emit(NetworkResult.Loading())
            val coinDetails = repository.getCoinDetails(coinId = coinId)
            emit(NetworkResult.Success(coinDetails))
        } catch (e: HttpException) {
            emit(NetworkResult.Failure(e.localizedMessage ?: "Unexpected error occurred"))
        } catch (e: IOException) {
            emit(NetworkResult.Failure(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}