package com.plcoding.cryptoinfo.presentation.coin_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptoinfo.common.Constants
import com.plcoding.cryptoinfo.common.NetworkResult
import com.plcoding.cryptoinfo.domain.use_cases.get_coin_details.GetCoinDetailsUseCase
import com.plcoding.cryptoinfo.domain.use_cases.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsListState())
    val state: State<CoinDetailsListState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            getCoinDetails(coinId = it)
        }
    }

    private fun getCoinDetails(coinId: String) {
        getCoinDetailsUseCase(coinId = coinId).onEach { result ->
            when (result) {
                is NetworkResult.Success -> {
                    _state.value = CoinDetailsListState(coinDetail = result.data)
                }
                is NetworkResult.Failure -> {
                    _state.value = CoinDetailsListState(
                        error = result.message ?: "Unexpected error occurred"
                    )
                }
                is NetworkResult.Loading -> {
                    _state.value = CoinDetailsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}