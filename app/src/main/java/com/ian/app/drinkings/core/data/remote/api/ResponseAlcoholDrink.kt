package com.ian.app.drinkings.core.data.remote.api

import com.google.gson.annotations.SerializedName

data class ResponseAlcoholDrink(
    @field:SerializedName("idDrink") val idDrink: String?,
    @field:SerializedName("strDrink") val strDrink: String?,
    @field:SerializedName("strDrinkThumb") val strDrinkThumb: String?,
)
