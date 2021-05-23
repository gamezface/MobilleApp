package com.gamezface.data.base

import com.gamezface.domain.exception.AppNetworkException
import com.gamezface.domain.exception.HttpClientErrorException
import com.gamezface.domain.exception.HttpServerErrorException
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRemoteDataSource {
    protected suspend fun <T : Any> getResult(call: suspend () -> T): T {
        try {
            return call()
        } catch (e: HttpException) {
            val exception = when (e.code()) {
                in 400..499 -> HttpClientErrorException(e.message(), e.code())
                else -> HttpServerErrorException(e.message(), e.code())
            }
            throw exception
        } catch (e: IOException) {
            throw AppNetworkException(e.message, e)
        } catch (e: Exception) {
            throw e
        }
    }
}