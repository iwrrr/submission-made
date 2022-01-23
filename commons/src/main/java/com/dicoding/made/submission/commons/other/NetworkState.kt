package com.dicoding.made.submission.commons.other

sealed class NetworkState<out R> {
    data class Success<out T>(val data: T) : NetworkState<T>()
    data class Error(val errorMessage: String) : NetworkState<Nothing>()
    object Empty : NetworkState<Nothing>()
}