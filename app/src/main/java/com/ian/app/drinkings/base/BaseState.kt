package com.ian.app.drinkings.base

import com.ian.app.drinkings.data.model.Drinks

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
sealed class BaseState

data class OnGetDrinksData(val data: Triple<List<Drinks>?, List<Drinks>?, List<Drinks>?>) : BaseState()
data class OnSuccessGetData(val show: Boolean) : BaseState()
data class OnFailedGetData(val msg: String?) : BaseState()