package com.ian.app.drinkings.core.data.local

import com.ian.app.drinkings.core.data.local.entity.EntityDetailAlcoholDrink
import kotlinx.coroutines.flow.Flow

interface AlcoholLocalDataSource {

    fun loadAllAlcoholDrinkData(): Flow<List<EntityDetailAlcoholDrink>>

    fun loadAllAlcoholDrinkDataById(id: Int): Flow<EntityDetailAlcoholDrink>

    fun insertAlcoholDrinkData(inputDrinkData: List<EntityDetailAlcoholDrink>)

    fun updateAlcoholDrinkData(updateDrinkData: EntityDetailAlcoholDrink)

    fun deleteAllAlcoholDrinkData()
}
