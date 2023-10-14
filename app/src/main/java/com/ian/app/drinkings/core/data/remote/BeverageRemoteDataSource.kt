package com.ian.app.drinkings.core.data.remote

import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.core.data.remote.api.ResponseAlcoholDrink
import com.ian.app.drinkings.core.data.remote.api.ResponseNonAlcoholDrink
import com.ian.app.drinkings.core.data.remote.model.GeneralDrinkData

interface BeverageRemoteDataSource {

    suspend fun getAlcoholicDrinks(): DataSource<GeneralDrinkData<List<ResponseAlcoholDrink>>>

    suspend fun getNonAlcoholicDrinks(): DataSource<GeneralDrinkData<List<ResponseNonAlcoholDrink>>>
}
