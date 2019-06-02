package com.ian.app.drinkings.ui.activity.filter

import com.ian.app.drinkings.base.BaseView
import com.ian.app.drinkings.data.model.Drinks

/**
 *
Created by Ian Damping on 01/06/2019.
Github = https://github.com/iandamping
 */
interface FilterView : BaseView {
    fun onGetFilterData(data: List<Drinks>?)
    fun onFailGetFilterData(msg: String?)
}