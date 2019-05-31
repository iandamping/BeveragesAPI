package com.ian.app.drinkings.ui.activity.detail

import com.ian.app.drinkings.base.BaseView
import com.ian.app.drinkings.data.model.Drinks

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
interface DetailDrinkView : BaseView {
    fun onSuccesGetData(data: Drinks?)
    fun onFailGetData(msg: String?)
}