package com.ian.app.drinkings.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class HomeSearchViewModel @Inject constructor() : ViewModel() {

    private val _uiSearchBeverageState: MutableStateFlow<String> = MutableStateFlow("")
    val uiSearchBeverageState: StateFlow<String> get() = _uiSearchBeverageState.asStateFlow()

    fun searchBeverage(query: String) {
        _uiSearchBeverageState.update { query }
    }
}
