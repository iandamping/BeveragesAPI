package com.ian.app.drinkings.data.viewmodel

import com.ian.app.drinkings.api.ApiInterface
import com.ian.app.drinkings.base.BaseViewModel
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.helper.BeverageConstant.alchoholState
import com.ian.app.helper.util.doSomethingWithDeferred

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class GetDiscoverDrinkViewModel(private val api: ApiInterface) : BaseViewModel() {

    fun getDiscoverDrinkData(state: String) {
        liveDataState.value = OnSuccessGetData(false)
        if (state == alchoholState) {
            uiScope.doSomethingWithDeferred(api.getAlchoholicDrinks(), {
                liveDataState.value = OnSuccessGetData(true)
                liveDataState.value = OnGetData(it.cocktailDrinks)
            }, {
                liveDataState.value = OnSuccessGetData(true)
                liveDataState.value = OnFailedGetData(it)
            })
        } else {
            uiScope.doSomethingWithDeferred(api.getNonAlchoholicDrinks(), {
                liveDataState.value = OnGetData(it.cocktailDrinks)
                liveDataState.value = OnSuccessGetData(true)
            }, {
                liveDataState.value = OnSuccessGetData(true)
                liveDataState.value = OnFailedGetData(it)
            })
        }
    }
}