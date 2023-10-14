package com.ian.app.drinkings.core.domain.repository

import com.ian.app.drinkings.core.domain.model.AlcoholDrink
import com.ian.app.drinkings.core.domain.model.common.DomainSource
import kotlinx.coroutines.flow.Flow

interface AlcoholBeverageRepository {

    suspend fun getAlcoholicDrinks(): DomainSource<List<AlcoholDrink>>

    fun loadAllAlcoholDrinkData(): Flow<List<AlcoholDrink>>

    fun loadAllAlcoholDrinkDataById(id: Int): Flow<AlcoholDrink>

    fun insertAlcoholDrinkData(inputDrinkData: List<AlcoholDrink>)

    fun updateAlcoholDrinkData(updateDrinkData: AlcoholDrink)

    fun deleteAllAlcoholDrinkData()
}
