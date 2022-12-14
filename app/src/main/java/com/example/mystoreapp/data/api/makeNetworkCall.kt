package com.example.mystoreapp.data.api

import com.example.mystoreapp.R
import com.example.mystoreapp.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): ResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ResponseStatus.Success(call())
    } catch (e: UnknownHostException) {
        ResponseStatus.Error(R.string.unknown_host_exception)
    } catch (e: Exception) {
        val errorMessage = R.string.network_error
        ResponseStatus.Error(errorMessage)
    }
}