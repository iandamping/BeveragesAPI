package com.ian.app.drinkings.core.data.remote

import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.core.data.remote.api.ResponseAlcoholDrink
import com.ian.app.drinkings.core.data.remote.api.ResponseDetailAlcoholDrink
import com.ian.app.drinkings.core.data.remote.api.ResponseDetailNonAlcoholDrink
import com.ian.app.drinkings.core.data.remote.api.ResponseNonAlcoholDrink
import com.ian.app.drinkings.core.data.remote.model.GeneralDrinkData

interface BeverageRemoteDataSource {

    suspend fun getAlcoholicDrinks(): DataSource<GeneralDrinkData<List<ResponseAlcoholDrink>>>

    suspend fun getAlcoholicDrinksById(id: Int): DataSource<ResponseDetailAlcoholDrink>

    suspend fun getNonAlcoholicDrinks(): DataSource<GeneralDrinkData<List<ResponseNonAlcoholDrink>>>

    suspend fun getNonAlcoholicDrinksById(id: Int): DataSource<ResponseDetailNonAlcoholDrink>
}
