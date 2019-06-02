package com.ian.app.drinkings.data.viewmodel

import com.ian.app.drinkings.api.ApiInterface
import com.ian.app.drinkings.base.BaseViewModel
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.helper.BeverageConstant.alchoholState
import com.ian.app.drinkings.helper.BeverageConstant.optionalAlchoholState
import com.ian.app.helper.util.doSomethingWithDeferred

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class GetDiscoverDrinkViewModel(private val api: ApiInterface) : BaseViewModel() {

    fun getDiscoverDrinkData(state: String) {
        when (state) {
            alchoholState -> uiScope.doSomethingWithDeferred(api.getAlchoholicDrinks(), {
                liveDataState.value = OnGetData(it.cocktailDrinks)
            }, {
                liveDataState.value = OnFailedGetData(it)
            })
            optionalAlchoholState -> uiScope.doSomethingWithDeferred(api.getOptionalAlchoholicDrinks(), {
                liveDataState.value = OnGetData(it.cocktailDrinks)
            }, {
                liveDataState.value = OnFailedGetData(it)
            })
            else -> uiScope.doSomethingWithDeferred(api.getNonAlchoholicDrinks(), {
                liveDataState.value = OnGetData(it.cocktailDrinks)
            }, {
                liveDataState.value = OnFailedGetData(it)
            })
        }
    }

    fun getDiscoverCategoryDrinkData(passedData: String) {
        uiScope.doSomethingWithDeferred(api.getFilterDataByCategory(passedData), {
            liveDataState.value = OnGetData(it.cocktailDrinks)
        }, {
            liveDataState.value = OnFailedGetData(it)
        })
    }

    fun getDiscoverGlassDrinkData(passedData: String) {
        uiScope.doSomethingWithDeferred(api.getFilterDataByGlass(passedData), {
            liveDataState.value = OnGetData(it.cocktailDrinks)
        }, {
            liveDataState.value = OnFailedGetData(it)
        })
    }

    fun getDiscoverIngredientDrinkData(passedData: String) {
        uiScope.doSomethingWithDeferred(api.getFilterDataByIngredients(passedData), {
            liveDataState.value = OnGetData(it.cocktailDrinks)
        }, {
            liveDataState.value = OnFailedGetData(it)
        })
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancel()
    }
}