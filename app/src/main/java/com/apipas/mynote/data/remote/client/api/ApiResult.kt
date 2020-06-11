package com.apipas.mynote.data.remote.client.api

import com.apipas.mynote.exception.RepositoryException
import retrofit2.Response

sealed class ApiResult<out T : Any> {
    data class Success<out T : Any>(val apiData: T, val res: Response<*>? = null) : ApiResult<T>()
    data class Error(val exception: RepositoryException, val res: Response<*>? = null) : ApiResult<Nothing>()
}