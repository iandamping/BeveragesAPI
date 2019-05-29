package com.ian.app.drinkings.ui.activity.main

import androidx.lifecycle.Observer
import com.ian.app.drinkings.base.BasePresenter
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.data.localdata.local_model.AlchoholDrink
import com.ian.app.drinkings.data.localdata.local_model.NonAlchoholDrink
import com.ian.app.drinkings.data.viewmodel.GetAllDrinksCoroutineViewModel

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
class MainPresenter(private val vm: GetAllDrinksCoroutineViewModel) : BasePresenter<MainView>() {

    override fun onCreate() {
        view()?.initView()
//        vm.getAlchoholData(getLifeCycleOwner())
//        vm.getNonAlchoholData(getLifeCycleOwner())
        vm.getDrinksData(getLifeCycleOwner())
        getData()
    }

    private fun getData() {
        vm.liveDataState.observe(getLifeCycleOwner(), Observer {
            when (it) {
                is OnSuccessGetData -> setDialogShow(it.show)
                is OnGetData<*> -> view()?.getDrinksData(it.data as Pair<List<AlchoholDrink>?, List<NonAlchoholDrink>?>)
            }
        })
    }

}