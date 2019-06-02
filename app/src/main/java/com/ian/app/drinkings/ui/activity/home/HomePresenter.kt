package com.ian.app.drinkings.ui.activity.home

import androidx.lifecycle.Observer
import com.ian.app.drinkings.base.*
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetAllDrinksViewModel

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
class HomePresenter(private val vm: GetAllDrinksViewModel) : BasePresenter<HomeView>() {
    private val ranges = 1..10
    private val smallRanges = 1..5
    override fun onCreate() {
        view()?.initView()
        vm.getDrinksData()
        getData()
    }

    private fun getData() {
        vm.liveDataState.observe(getLifeCycleOwner(), Observer {
            when (it) {
                is OnGetDrinksData -> {
                    nonAlchoholDrinksMapper(it.data.first)
                    alchoholDrinksMapper(it.data.second)
                    view()?.getOptionalAlchoholDrink(it.data.third)
                    getHeadlineDrinks(Triple(it.data.first,it.data.second,it.data.third))
                }
                is OnGetRandomDrinkData -> view()?.getRandomDrink(it.data?.get(0))
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

    private fun getHeadlineDrinks(data:Triple<List<Drinks>?,List<Drinks>?,List<Drinks>?>){
        val newData: MutableList<Drinks> = mutableListOf()
        for (i in smallRanges){
            if (data.first!=null){
                newData.add(data.first!![i])
            }
            if (data.second!=null){
                newData.add(data.second!![i])
            }
            if (data.third!=null){
                newData.add(data.third!![i])
            }
        }
        view()?.getHeadlineDrinks(newData)
    }

}