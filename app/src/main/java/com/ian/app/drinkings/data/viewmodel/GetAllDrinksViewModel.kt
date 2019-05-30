package com.ian.app.drinkings.data.viewmodel

import com.ian.app.drinkings.api.ApiInterface
import com.ian.app.drinkings.base.BaseViewModel
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetDrinksData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.helper.deferredTriple

/**
 *
Created by Ian Damping on 28/05/2019.
Github = https://github.com/iandamping
 */
class GetAllDrinksViewModel(private val api: ApiInterface) : BaseViewModel() {

    fun getDrinksData() {
        liveDataState.value = OnSuccessGetData(false)
        uiScope.deferredTriple(
                Triple(api.getNonAlchoholicDrinks(), api.getAlchoholicDrinks(), api.getRandomDrink()),
                { first, second, third ->
                    liveDataState.value = OnSuccessGetData(true)
                    liveDataState.value =
                            OnGetDrinksData(Triple(first.cocktailDrinks, second.cocktailDrinks, third.cocktailDrinks))
                }) {
            liveDataState.value = OnSuccessGetData(true)
            liveDataState.value = OnFailedGetData(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancel()
    }
}