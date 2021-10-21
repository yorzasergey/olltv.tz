package com.olltv.tz.data.network.datasource


import com.olltv.tz.Resource
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.Success(body)
            }
            return Resource.Error(Exception("code = ${response.code()}; message = ${response.message()}"))
        } catch (e: Throwable) {
            return Resource.Error(e)
        }
    }
}