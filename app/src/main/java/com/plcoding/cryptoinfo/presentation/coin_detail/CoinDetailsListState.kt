package com.plcoding.cryptoinfo.presentation.coin_detail

import com.plcoding.cryptoinfo.data.remote.dto.CoinDetailDto
import com.plcoding.cryptoinfo.data.remote.dto.CoinDto

data class CoinDetailsListState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetailDto? = null,
    val error: String? = null
) {
}