package com.ian.app.drinkings.ui.activity.filter

import androidx.lifecycle.Observer
import com.ian.app.drinkings.base.BasePresenter
import com.ian.app.drinkings.base.BaseState
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetFilterDrinksViewModel
import com.ian.app.drinkings.helper.BeverageConstant.filterByCategory
import com.ian.app.drinkings.helper.BeverageConstant.filterByGlass
import com.ian.app.drinkings.helper.BeverageConstant.filterByIngredient

/**
 *
Created by Ian Damping on 01/06/2019.
Github = https://github.com/iandamping
 */
class FilterPresenter(private val vm: GetFilterDrinksViewModel) : BasePresenter<FilterView>() {

    override fun onCreate() {
        view()?.initView()
    }

    fun onGetFilterData(filterState: String) {
        when (filterState) {
            filterByIngredient -> {
                vm.getFilterData(filterState).apply {
                    vm.liveDataState.observe(getLifeCycleOwner(), Observer {
                        extractData(it)
                    })
                }
            }
            filterByGlass -> {
                vm.getFilterData(filterState).apply {
                    vm.liveDataState.observe(getLifeCycleOwner(), Observer {
                        extractData(it)
                    })
                }
            }
            filterByCategory -> {
                vm.getFilterData(filterState).apply {
                    vm.liveDataState.observe(getLifeCycleOwner(), Observer {
                        extractData(it)
                    })
                }
            }
        }
    }

    private fun extractData(data: BaseState) {
        when (data) {
            is OnFailedGetData -> view()?.onFailGetFilterData(data.msg)
            is OnGetData<*> -> view()?.onGetFilterData(data.data as List<Drinks>?)
        }
    }


}