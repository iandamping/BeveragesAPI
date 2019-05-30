package com.ian.app.drinkings.ui.activity.main

import androidx.lifecycle.Observer
import com.ian.app.drinkings.base.BasePresenter
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetAllDrinksViewModel

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
class MainPresenter(private val vm: GetAllDrinksViewModel) : BasePresenter<MainView>() {

    override fun onCreate() {
        view()?.initView()
        vm.getDrinksData()
        getData()
    }

    private fun getData() {
        vm.liveDataState.observe(getLifeCycleOwner(), Observer {
            when (it) {
                is OnSuccessGetData -> setDialogShow(it.show)
                is OnGetData<*> -> view()?.getDrinksData(it.data as Triple<List<Drinks>?, List<Drinks>?, List<Drinks>?>)
            }
        })
    }

}