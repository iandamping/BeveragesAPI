package com.ian.app.drinkings.core.data.local

import com.ian.app.drinkings.core.data.local.entity.EntityDetailNonAlcoholDrink
import kotlinx.coroutines.flow.Flow

interface NonAlcoholLocalDataSource {

    fun loadAllNonAlcoholDrinkData(): Flow<List<EntityDetailNonAlcoholDrink>>

    fun loadAllNonAlcoholDrinkDataById(id: Int): Flow<EntityDetailNonAlcoholDrink>

    fun insertNonAlcoholDrinkData(inputDrinkData: List<EntityDetailNonAlcoholDrink>)

    fun updateNonAlcoholDrinkData(updateDrinkData: EntityDetailNonAlcoholDrink)

    fun deleteAllNonAlcoholDrinkData()
}
