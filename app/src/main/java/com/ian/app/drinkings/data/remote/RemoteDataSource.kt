package com.ian.app.drinkings.data.remote

import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.data.remote.api.ResponseAlcoholDrink
import com.ian.app.drinkings.data.remote.api.ResponseNonAlcoholDrink
import com.ian.app.drinkings.data.remote.model.GeneralDrinkData

interface RemoteDataSource {

    suspend fun getAlcoholicDrinks(): DataSource<GeneralDrinkData<ResponseAlcoholDrink>>

    suspend fun getNonAlcoholicDrinks(): DataSource<GeneralDrinkData<ResponseNonAlcoholDrink>>
}
