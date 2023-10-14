package com.ian.app.drinkings.core.data.local

import com.ian.app.drinkings.core.data.local.entity.EntityAlcoholDrink
import kotlinx.coroutines.flow.Flow

interface AlcoholLocalDataSource {

    fun loadAllAlcoholDrinkData(): Flow<List<EntityAlcoholDrink>>

    fun loadAllAlcoholDrinkDataById(id: Int): Flow<EntityAlcoholDrink>

    fun insertAlcoholDrinkData(inputDrinkData: List<EntityAlcoholDrink>)

    fun updateAlcoholDrinkData(updateDrinkData: EntityAlcoholDrink)

    fun deleteAllAlcoholDrinkData()
}
