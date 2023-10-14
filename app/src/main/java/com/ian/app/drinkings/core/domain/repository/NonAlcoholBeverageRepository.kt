package com.ian.app.drinkings.core.domain.repository

import com.ian.app.drinkings.core.domain.model.NonAlcoholDrink
import com.ian.app.drinkings.core.domain.model.common.DomainSource
import kotlinx.coroutines.flow.Flow

interface NonAlcoholBeverageRepository {

    suspend fun getNonAlcoholicDrinks(): DomainSource<List<NonAlcoholDrink>>

    fun loadAllNonAlcoholDrinkData(): Flow<List<NonAlcoholDrink>>

    fun loadAllNonAlcoholDrinkDataById(id: Int): Flow<NonAlcoholDrink>

    fun insertNonAlcoholDrinkData(inputDrinkData: List<NonAlcoholDrink>)

    fun updateNonAlcoholDrinkData(updateDrinkData: NonAlcoholDrink)

    fun deleteAllNonAlcoholDrinkData()
}
