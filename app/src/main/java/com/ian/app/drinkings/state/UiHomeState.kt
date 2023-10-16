package com.ian.app.drinkings.state

sealed interface UiHomeState {

    data class Success<out T>(val data: T) : UiHomeState

    data class Error(val errorMessage: String) : UiHomeState

    object Loading : UiHomeState
}
