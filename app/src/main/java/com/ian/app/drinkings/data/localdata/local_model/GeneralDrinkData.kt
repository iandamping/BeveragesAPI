package com.ian.app.drinkings.data.localdata.local_model

import com.google.gson.annotations.SerializedName

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
data class GeneralDrinkData<T>(@field:SerializedName("drinks") val cocktailDrinks: List<T>?)