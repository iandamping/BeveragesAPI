package com.ian.app.drinkings.core.data.local

import com.ian.app.drinkings.core.data.local.entity.EntityNonAlcoholDrink
import kotlinx.coroutines.flow.Flow

interface NonAlcoholLocalDataSource {

    fun loadAllNonAlcoholDrinkData(): Flow<List<EntityNonAlcoholDrink>>

    fun loadAllNonAlcoholDrinkDataById(id: Int): Flow<EntityNonAlcoholDrink>

    fun insertNonAlcoholDrinkData(inputDrinkData: List<EntityNonAlcoholDrink>)

    fun updateNonAlcoholDrinkData(updateDrinkData: EntityNonAlcoholDrink)

    fun deleteAllNonAlcoholDrinkData()
}
