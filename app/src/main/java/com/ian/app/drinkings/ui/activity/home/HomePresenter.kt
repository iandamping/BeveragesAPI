package com.ian.app.drinkings.ui.activity.home

import androidx.lifecycle.Observer
import com.ian.app.drinkings.base.BasePresenter
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetDrinksData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetAllDrinksViewModel

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
class HomePresenter(private val vm: GetAllDrinksViewModel) : BasePresenter<HomeView>() {
    private val ranges = 1..10
    override fun onCreate() {
        view()?.initView()
        vm.getDrinksData()
        getData()
    }

    private fun getData() {
        vm.liveDataState.observe(getLifeCycleOwner(), Observer {
            when (it) {
                is OnSuccessGetData -> setDialogShow(it.show)
                is OnGetDrinksData -> {
                    nonAlchoholDrinksMapper(it.data.first)
                    alchoholDrinksMapper(it.data.second)

                    view()?.getRandomDrink(it.data.third?.get(0))
                }
                is OnFailedGetData -> view()?.onFailedGetDrink(it.msg)
            }
        })
    }

    private fun alchoholDrinksMapper(data: List<Drinks>?) {
        val newData: MutableList<Drinks> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        view()?.getAlchoholDrink(newData)
    }

    private fun nonAlchoholDrinksMapper(data: List<Drinks>?) {
        val newData: MutableList<Drinks> = mutableListOf()
        for (i in ranges) {
            if (data != null) {
                newData.add(data[i])
            }
        }
        view()?.getNonAlcoholDrink(newData)
    }

}