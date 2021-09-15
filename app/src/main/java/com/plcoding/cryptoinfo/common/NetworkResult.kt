package com.plcoding.cryptoinfo.common

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T?) : NetworkResult<T>()
    data class Failure(val message: String? = null) : NetworkResult<Nothing>()
    data class Loading(var loading: Boolean = false) : NetworkResult<Nothing>()
}