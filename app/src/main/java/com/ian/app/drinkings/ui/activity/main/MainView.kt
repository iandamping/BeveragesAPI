package com.ian.app.drinkings.ui.activity.main

import com.ian.app.drinkings.base.BaseView
import com.ian.app.drinkings.data.localdata.local_model.AlchoholDrink
import com.ian.app.drinkings.data.localdata.local_model.NonAlchoholDrink

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
interface MainView : BaseView {
    fun getDrinksData(data: Pair<List<AlchoholDrink>?, List<NonAlchoholDrink>?>)
}