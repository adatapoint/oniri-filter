package com.vince.onirifilter.data

sealed class CallResult<out T> {
    data class Success<out T>(val data: T) : CallResult<T>()
    data class Failure(val t: Throwable) : CallResult<Nothing>()

    companion object {
        fun <T> success(data: T): CallResult<T> = Success(data)
        fun <T> failure(t: Throwable): CallResult<Nothing> = Failure(t)
    }
}
