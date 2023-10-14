package com.ian.app.drinkings.core.domain.model.common

sealed class DomainSource<out T> {

    data class Success<out T>(val data: T) : DomainSource<T>()

    data class Error(val errorMessage: String) : DomainSource<Nothing>()
}
