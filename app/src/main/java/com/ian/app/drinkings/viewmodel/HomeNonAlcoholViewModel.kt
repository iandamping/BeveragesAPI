package com.ian.app.drinkings.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.drinkings.core.domain.model.common.DomainSource
import com.ian.app.drinkings.core.domain.repository.NonAlcoholBeverageRepository
import com.ian.app.drinkings.state.UiHomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeNonAlcoholViewModel @Inject constructor(
    private val nonAlcoholBeverageRepository: NonAlcoholBeverageRepository
) : ViewModel() {

    var homeNonAlcoholUiState: UiHomeState by mutableStateOf(UiHomeState.Loading)
        private set

    init {
        viewModelScope.launch {
            homeNonAlcoholUiState =
                when (val data = nonAlcoholBeverageRepository.getNonAlcoholicDrinks()) {
                    is DomainSource.Error -> UiHomeState.Error(data.errorMessage)
                    is DomainSource.Success -> UiHomeState.Success(data.data)
                }
        }
    }
}
