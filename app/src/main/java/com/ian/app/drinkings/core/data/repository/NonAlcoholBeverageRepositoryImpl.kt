package com.ian.app.drinkings.core.data.repository

import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.core.data.local.NonAlcoholLocalDataSource
import com.ian.app.drinkings.core.data.remote.BeverageRemoteDataSource
import com.ian.app.drinkings.core.domain.model.NonAlcoholDrink
import com.ian.app.drinkings.core.domain.model.common.DomainSource
import com.ian.app.drinkings.core.domain.model.mapper.mapLocalNonAlcoholDrinkToDomain
import com.ian.app.drinkings.core.domain.model.mapper.mapNonAlcoholDrinkToData
import com.ian.app.drinkings.core.domain.model.mapper.mapRemoteNonAlcoholDrinkToDomain
import com.ian.app.drinkings.core.domain.repository.NonAlcoholBeverageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NonAlcoholBeverageRepositoryImpl @Inject constructor(
    private val localDataSource: NonAlcoholLocalDataSource,
    private val remoteDataSource: BeverageRemoteDataSource
) :
    NonAlcoholBeverageRepository {
    override suspend fun getNonAlcoholicDrinks(): DomainSource<List<NonAlcoholDrink>> {
        return when (val data = remoteDataSource.getNonAlcoholicDrinks()) {
            is DataSource.Error -> DomainSource.Error(data.errorMessage)
            is DataSource.Success -> DomainSource.Success(
                data.data.cocktailDrinks.map { it.mapRemoteNonAlcoholDrinkToDomain() }
            )
        }
    }

    override fun loadAllNonAlcoholDrinkData(): Flow<List<NonAlcoholDrink>> {
        return localDataSource.loadAllNonAlcoholDrinkData()
            .map { data -> data.map { it.mapLocalNonAlcoholDrinkToDomain() } }
    }

    override fun loadAllNonAlcoholDrinkDataById(id: Int): Flow<NonAlcoholDrink> {
        return localDataSource.loadAllNonAlcoholDrinkDataById(id)
            .map { it.mapLocalNonAlcoholDrinkToDomain() }
    }

    override fun insertNonAlcoholDrinkData(inputDrinkData: List<NonAlcoholDrink>) {
        localDataSource.insertNonAlcoholDrinkData(inputDrinkData.map { it.mapNonAlcoholDrinkToData() })
    }

    override fun updateNonAlcoholDrinkData(updateDrinkData: NonAlcoholDrink) {
        localDataSource.updateNonAlcoholDrinkData(updateDrinkData.mapNonAlcoholDrinkToData())
    }

    override fun deleteAllNonAlcoholDrinkData() {
        localDataSource.deleteAllNonAlcoholDrinkData()
    }
}
