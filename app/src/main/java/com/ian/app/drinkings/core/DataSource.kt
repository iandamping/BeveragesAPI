package com.ian.app.drinkings.core

sealed class DataSource<out T> {
    data class Success<out T>(val data: T) : DataSource<T>()
    data class Error(val errorMessage: String) : DataSource<Nothing>()
}
