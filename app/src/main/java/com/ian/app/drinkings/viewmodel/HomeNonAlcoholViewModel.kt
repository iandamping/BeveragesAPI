package com.ian.app.drinkings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.drinkings.core.domain.model.common.DomainSource
import com.ian.app.drinkings.core.domain.repository.NonAlcoholBeverageRepository
import com.ian.app.drinkings.state.UiHomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeNonAlcoholViewModel @Inject constructor(
    private val nonAlcoholBeverageRepository: NonAlcoholBeverageRepository
) : ViewModel() {

    private var _homeNonAlcoholUiState: MutableStateFlow<UiHomeState> =
        MutableStateFlow(UiHomeState.Loading)
    val homeNonAlcoholUiState: StateFlow<UiHomeState> get() = _homeNonAlcoholUiState.asStateFlow()

    init {
        getNonAlcoholData()
    }

    fun getNonAlcoholData() {
        viewModelScope.launch {
            when (val data = nonAlcoholBeverageRepository.getNonAlcoholicDrinks()) {
                is DomainSource.Error -> _homeNonAlcoholUiState.update {
                    UiHomeState.Error(data.errorMessage)
                }

                is DomainSource.Success -> _homeNonAlcoholUiState.update {
                    UiHomeState.Success(data.data)
                }
            }
        }
    }
}
