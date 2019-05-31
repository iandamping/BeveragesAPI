package com.ian.app.drinkings.data.viewmodel

import com.ian.app.drinkings.api.ApiInterface
import com.ian.app.drinkings.base.BaseViewModel
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetDetailDrinkData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.helper.doSomethingWithDeferred

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class GetDetailDrinkViewModel(private val api: ApiInterface) : BaseViewModel() {

    fun getDetailDrink(drinkID: String) {
        liveDataState.value = OnSuccessGetData(false)
        uiScope.doSomethingWithDeferred(api.getDetailedDrink(drinkID), {
            liveDataState.value = OnGetDetailDrinkData(it.cocktailDrinks?.get(0))
            liveDataState.value = OnSuccessGetData(true)
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