package com.olltv.tz.utils

import com.olltv.tz.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T, A> suspendNetworkOperation(
    networkCall: suspend () -> Resource<A>,
    mapper: (A) -> T
): Resource<T> {
    return withContext(Dispatchers.IO) {
        val networkResponse = networkCall.invoke()
        when(networkResponse){
            is Resource.Success -> {
                val data = mapper(networkResponse.data!!)
                Resource.Success(data)
            }
            is Resource.Error-> Resource.Error(networkResponse.exception)
            else -> throw IllegalStateException("Not supported PagingSource state: $networkResponse")
        }
    }
}
