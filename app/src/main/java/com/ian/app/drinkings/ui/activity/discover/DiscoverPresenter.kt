package com.ian.app.drinkings.ui.activity.discover

import androidx.lifecycle.Observer
import com.ian.app.drinkings.DrinkingsApp.Companion.gson
import com.ian.app.drinkings.base.BasePresenter
import com.ian.app.drinkings.base.BaseState
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetDiscoverDrinkViewModel

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class DiscoverPresenter(private val vm: GetDiscoverDrinkViewModel) : BasePresenter<DiscoverView>() {

    override fun onCreate() {
        view()?.initView()
    }

    fun getDiscoverData(drinksState: String?) {
        if (drinksState != null) {
            vm.getDiscoverDrinkData(drinksState).apply {
                vm.liveDataState.observe(getLifeCycleOwner(), Observer { extractData(it) })
            }
        }
    }

    fun getPassedDiscoverData(passedData: String?) {
        if (passedData != null) {
            val tempData = gson.fromJson(passedData, Drinks::class.java)
            when {
                !tempData.strGlass.isNullOrEmpty() -> {
                    vm.getDiscoverGlassDrinkData(tempData.strGlass).apply {
                        vm.liveDataState.observe(getLifeCycleOwner(), Observer { extractData(it) })
                    }
                }
                !tempData.strCategory.isNullOrEmpty() -> {
                    vm.getDiscoverCategoryDrinkData(tempData.strCategory).apply {
                        vm.liveDataState.observe(getLifeCycleOwner(), Observer { extractData(it) })
                    }
                }
                !tempData.strIngredient1.isNullOrEmpty() -> {
                    vm.getDiscoverIngredientDrinkData(tempData.strIngredient1).apply {
                        vm.liveDataState.observe(getLifeCycleOwner(), Observer { extractData(it) })
                    }
                }
            }
        }
    }

    private fun extractData(data: BaseState) {
        when (data) {
            is OnFailedGetData -> view()?.onFailGetData(data.msg)
            is OnGetData<*> -> view()?.onSuccessGetData(data.data as List<Drinks>?)
        }
    }
}