package com.ian.app.drinkings.ui.activity.discover

import androidx.lifecycle.Observer
import com.ian.app.drinkings.base.BasePresenter
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.base.OnSuccessGetData
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

    fun getDiscoverData(drinksState: String) {
        vm.getDiscoverDrinkData(drinksState).apply {
            vm.liveDataState.observe(getLifeCycleOwner(), Observer {
                when (it) {
                    is OnSuccessGetData -> setDialogShow(it.show)
                    is OnFailedGetData -> view()?.onFailGetData(it.msg)
                    is OnGetData<*> -> view()?.onSuccessGetData(it.data as List<Drinks>?)
                }
            })
        }
    }
}