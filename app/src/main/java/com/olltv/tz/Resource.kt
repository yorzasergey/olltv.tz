package com.olltv.tz

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Resource<out R> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Resource<*>.succeeded
    get() = this is Resource.Success && data != null

fun <T> Resource<T>.successOr(fallback: T): T {
    return (this as? Resource.Success<T>)?.data ?: fallback
}


/*fun <R, T : R> Result<T>.successOrElse(onFailure: (exception: Throwable) -> R): R {

    when(this){
        is Result.Success<T> -> return data
        is Result.Error -> return onFailure(exception)
    }
}*/
