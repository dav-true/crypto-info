package com.plcoding.cryptoinfo.presentation.coin_list

import com.plcoding.cryptoinfo.data.remote.dto.CoinDto

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinDto> = emptyList(),
    val error: String? = null
) {
}