package com.apipas.mynote.data.remote.client.api

import androidx.annotation.StringRes
import com.apipas.mynote.App
import com.apipas.mynote.R
import com.apipas.mynote.exception.RepositoryErrorType
import com.apipas.mynote.exception.RepositoryException
import com.apipas.mynote.util.Log
import com.apipas.mynote.util.NetworkUtil
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


@Suppress("UNCHECKED_CAST")
suspend fun <T : Any, D : Any> safeApiCall(
    call: suspend () -> Response<T>,
    mapper: (m: T) -> D
): ApiResult<D> {
    lateinit var res: Response<T>
    try {
        if (!NetworkUtil.verifyAvailableNetwork(App.instance)) {
            return ApiResult.Error(
                RepositoryException(
                    RepositoryErrorType.TYPE_NETWORK,
                    message = App.instance.getString(R.string.global_no_network_connection)
                )
            )
        }
        res = call.invoke()
        Log.d("res:$res")
        return if (res.body() == null) {
            ApiResult.Success(Unit as D, res)
        } else if (res.isSuccessful) {
            ApiResult.Success((mapper(res.body()!!)), res)
        } else {
            ApiResult.Error(getGeneralApiException(R.string.global_server_error), res)
        }

    } catch (e: Exception) {
        Log.e("resError:${e.message}")
        return ApiResult.Error(
            when (e) {
                is RepositoryException -> e
                is HttpException -> RepositoryException(RepositoryErrorType.TYPE_HTTP, cause = e)
                is IOException -> RepositoryException(RepositoryErrorType.TYPE_NETWORK, cause = e)
                else -> RepositoryException(RepositoryErrorType.TYPE_UNEXPECTED, cause = e)
            }
        )
    }
}

private fun getGeneralApiException(@StringRes msg: Int): RepositoryException {
    return RepositoryException(
        RepositoryErrorType.TYPE_API,
        // Message = "Error Http Code!"
        message = App.instance.getString(msg)
    )
}