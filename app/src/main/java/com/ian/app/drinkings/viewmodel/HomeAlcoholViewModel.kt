package com.ian.app.drinkings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.app.drinkings.core.domain.model.AlcoholDrink
import com.ian.app.drinkings.core.domain.model.common.DomainSource
import com.ian.app.drinkings.core.domain.repository.AlcoholBeverageRepository
import com.ian.app.drinkings.state.UiHomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeAlcoholViewModel @Inject constructor(
    private val alcoholBeverageRepository: AlcoholBeverageRepository,
) : ViewModel() {

    private var _homeAlcoholUiState: MutableStateFlow<UiHomeState<List<AlcoholDrink>>> =
        MutableStateFlow(UiHomeState.Loading)
    val homeAlcoholUiState: StateFlow<UiHomeState<List<AlcoholDrink>>>
        get() = _homeAlcoholUiState.asStateFlow()

    init {
        getAlcoholData()
    }

    fun getAlcoholData() {
        viewModelScope.launch {
            when (val data = alcoholBeverageRepository.getAlcoholicDrinks()) {
                is DomainSource.Error -> _homeAlcoholUiState.update {
                    UiHomeState.Error(data.errorMessage)
                }

                is DomainSource.Success -> _homeAlcoholUiState.update {
                    UiHomeState.Success(data.data)
                }
            }
        }
    }
}
