package com.ian.app.drinkings.ui.activity.main

import com.ian.app.drinkings.base.BaseView
import com.ian.app.drinkings.data.model.Drinks

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
interface MainView : BaseView {
    fun getDrinksData(data: Triple<List<Drinks>?, List<Drinks>?, List<Drinks>?>)
}