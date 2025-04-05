package com.dragan.androidtestapp.domain

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Result<T>(data, null)
    class Error<T>(message: String, data: T? = null): Result<T>(data, message)
    class Loading<T>(data: T? = null): Result<T>(data)
}