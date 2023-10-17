package com.ian.app.drinkings.core.domain.repository

import com.ian.app.drinkings.core.domain.model.AlcoholDrink
import com.ian.app.drinkings.core.domain.model.DetailAlcoholDrink
import com.ian.app.drinkings.core.domain.model.common.DomainSource
import kotlinx.coroutines.flow.Flow

interface AlcoholBeverageRepository {

    suspend fun getAlcoholicDrinks(): DomainSource<List<AlcoholDrink>>

    suspend fun getAlcoholicDrinksById(id: Int): DomainSource<DetailAlcoholDrink>

    fun loadDetailAllAlcoholDrinkData(): Flow<List<DetailAlcoholDrink>>

    fun loadDetailAllAlcoholDrinkDataById(id: Int): Flow<DetailAlcoholDrink>

    fun insertDetailAlcoholDrinkData(inputDrinkData: List<DetailAlcoholDrink>)

    fun updateDetailAlcoholDrinkData(updateDrinkData: DetailAlcoholDrink)

    fun deleteAllDetailAlcoholDrinkData()
}
