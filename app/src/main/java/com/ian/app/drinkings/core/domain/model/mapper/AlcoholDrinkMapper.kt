package com.ian.app.drinkings.core.domain.model.mapper

import com.ian.app.drinkings.core.data.remote.api.ResponseAlcoholDrink
import com.ian.app.drinkings.core.domain.model.AlcoholDrink

fun ResponseAlcoholDrink.mapRemoteAlcoholDrinkToDomain(): AlcoholDrink {
    return AlcoholDrink(remoteIdDrink = idDrink, strDrink = strDrink, strDrinkThumb = strDrinkThumb)
}
