package com.ian.app.drinkings.data.viewmodel

import com.ian.app.drinkings.api.ApiInterface
import com.ian.app.drinkings.base.BaseViewModel
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.di.NetworkingModule.getListsData
import com.ian.app.drinkings.helper.BeverageConstant.filterByCategory
import com.ian.app.drinkings.helper.BeverageConstant.filterByGlass
import com.ian.app.drinkings.helper.BeverageConstant.filterByIngredient
import com.ian.app.helper.util.doSomethingWithDeferred

/**
 *
Created by Ian Damping on 01/06/2019.
Github = https://github.com/iandamping
 */
class GetFilterDrinksViewModel(private val api: ApiInterface) : BaseViewModel() {

    fun getFilterData(state: String) {
        when (state) {
            filterByCategory -> {
                uiScope.doSomethingWithDeferred(api.getAllDrinkCategories(getListsData), {
                    liveDataState.value = OnGetData(it.cocktailDrinks)
                }, {
                    liveDataState.value = OnFailedGetData(it)
                })
            }
            filterByGlass -> {
                uiScope.doSomethingWithDeferred(api.getAllDrinkGlasses(getListsData), {
                    liveDataState.value = OnGetData(it.cocktailDrinks)
                }, {
                    liveDataState.value = OnFailedGetData(it)
                })
            }
            filterByIngredient -> {
                uiScope.doSomethingWithDeferred(api.getAllDrinkIngredients(getListsData), {
                    liveDataState.value = OnGetData(it.cocktailDrinks)
                }, {
                    liveDataState.value = OnFailedGetData(it)
                })
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancel()
    }

}