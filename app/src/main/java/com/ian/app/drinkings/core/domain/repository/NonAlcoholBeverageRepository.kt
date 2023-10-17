package com.ian.app.drinkings.core.domain.repository

import com.ian.app.drinkings.core.domain.model.DetailNonAlcoholDrink
import com.ian.app.drinkings.core.domain.model.NonAlcoholDrink
import com.ian.app.drinkings.core.domain.model.common.DomainSource
import kotlinx.coroutines.flow.Flow

interface NonAlcoholBeverageRepository {

    suspend fun getNonAlcoholicDrinks(): DomainSource<List<NonAlcoholDrink>>

    suspend fun getNonAlcoholicDrinksById(id: Int): DomainSource<DetailNonAlcoholDrink>

    fun loadAllDetailNonAlcoholDrinkData(): Flow<List<DetailNonAlcoholDrink>>

    fun loadAllDetailNonAlcoholDrinkDataById(id: Int): Flow<DetailNonAlcoholDrink>

    fun insertDetailNonAlcoholDrinkData(inputDrinkData: List<DetailNonAlcoholDrink>)

    fun updateDetailNonAlcoholDrinkData(updateDrinkData: DetailNonAlcoholDrink)

    fun deleteAllDetailNonAlcoholDrinkData()
}
