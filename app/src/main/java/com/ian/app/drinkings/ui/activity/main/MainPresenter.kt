package com.ian.app.drinkings.ui.activity.main

import androidx.lifecycle.Observer
import com.ian.app.drinkings.base.BasePresenter
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.data.localdata.CocktailDrink
import com.ian.app.drinkings.data.viewmodel.MargaritaViewModel

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
class MainPresenter(private val vm: MargaritaViewModel) : BasePresenter<MainView>() {

    override fun onCreate() {
        view()?.initView()
        vm.getMargaritaData(getLifeCycleOwner())
        getData()
    }

    private fun getData() {
        vm.liveDataState.observe(getLifeCycleOwner(), Observer {
            when (it) {
                is OnSuccessGetData -> setDialogShow(it.show)
                is OnGetData<*> -> view()?.showMargaritaData(it.data as List<CocktailDrink.Drink>)
            }
        })
    }

}