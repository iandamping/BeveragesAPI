package com.ian.app.drinkings.state

sealed interface UiHomeState<out T> {

    data class Success<out T>(val data: T) : UiHomeState<T>

    data class Error(val errorMessage: String) : UiHomeState<Nothing>

    object Loading : UiHomeState<Nothing>
}
