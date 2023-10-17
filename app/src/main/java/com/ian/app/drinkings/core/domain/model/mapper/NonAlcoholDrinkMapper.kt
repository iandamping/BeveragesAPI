package com.ian.app.drinkings.core.domain.model.mapper

import com.ian.app.drinkings.core.data.remote.api.ResponseNonAlcoholDrink
import com.ian.app.drinkings.core.domain.model.NonAlcoholDrink

fun ResponseNonAlcoholDrink.mapRemoteNonAlcoholDrinkToDomain(): NonAlcoholDrink {
    return NonAlcoholDrink(
        remoteIdDrink = idDrink,
        strDrink = strDrink,
        strDrinkThumb = strDrinkThumb
    )
}
