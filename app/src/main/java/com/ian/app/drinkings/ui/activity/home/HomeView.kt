package com.ian.app.drinkings.ui.activity.home

import com.ian.app.drinkings.base.BaseView
import com.ian.app.drinkings.data.model.Drinks

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
interface HomeView : BaseView {
    fun getAlchoholDrink(data: List<Drinks>?)
    fun getNonAlcoholDrink(data: List<Drinks>?)
    fun getOptionalAlchoholDrink(data: List<Drinks>?)
    fun getRandomDrink(data: Drinks?)
    fun onFailedGetDrink(msg: String?)
}