package com.ian.app.drinkings.data.viewmodel

import com.ian.app.drinkings.api.ApiInterface
import com.ian.app.drinkings.base.*
import com.ian.app.helper.util.combineTripleWithSingleDeferred

/**
 *
Created by Ian Damping on 28/05/2019.
Github = https://github.com/iandamping
 */
class GetAllDrinksViewModel(private val api: ApiInterface) : BaseViewModel() {


    fun getDrinksData() {
        liveDataState.value = OnSuccessGetData(false)
        uiScope.combineTripleWithSingleDeferred(
            Pair(
                Triple(api.getNonAlchoholicDrinks(), api.getAlchoholicDrinks(), api.getOptionalAlchoholicDrinks()),
                api.getRandomDrink()
            )
            , {
                liveDataState.value = OnSuccessGetData(true)
                liveDataState.value = OnGetRandomDrinkData(it.second.cocktailDrinks)
                liveDataState.value = OnGetDrinksData(
                    Triple(
                        it.first.first.cocktailDrinks,
                        it.first.second.cocktailDrinks,
                        it.first.third.cocktailDrinks
                    )
                )
            }, {
                liveDataState.value = OnSuccessGetData(true)
                liveDataState.value = OnFailedGetData(it)
            })
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancel()
    }
}