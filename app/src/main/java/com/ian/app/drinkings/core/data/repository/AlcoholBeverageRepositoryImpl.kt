package com.ian.app.drinkings.core.data.repository

import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.core.data.local.AlcoholLocalDataSource
import com.ian.app.drinkings.core.data.remote.BeverageRemoteDataSource
import com.ian.app.drinkings.core.domain.model.AlcoholDrink
import com.ian.app.drinkings.core.domain.model.common.DomainSource
import com.ian.app.drinkings.core.domain.model.mapper.mapAlcoholDrinkToData
import com.ian.app.drinkings.core.domain.model.mapper.mapLocalAlcoholDrinkToDomain
import com.ian.app.drinkings.core.domain.model.mapper.mapRemoteAlcoholDrinkToDomain
import com.ian.app.drinkings.core.domain.repository.AlcoholBeverageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlcoholBeverageRepositoryImpl @Inject constructor(
    private val remoteDataSource: BeverageRemoteDataSource,
    private val localDataSource: AlcoholLocalDataSource
) :
    AlcoholBeverageRepository {
    override suspend fun getAlcoholicDrinks(): DomainSource<List<AlcoholDrink>> {
        return when (val data = remoteDataSource.getAlcoholicDrinks()) {
            is DataSource.Error -> DomainSource.Error(data.errorMessage)
            is DataSource.Success -> DomainSource.Success(
                data.data.cocktailDrinks.map { it.mapRemoteAlcoholDrinkToDomain() }
            )
        }
    }

    override fun loadAllAlcoholDrinkData(): Flow<List<AlcoholDrink>> {
        return localDataSource.loadAllAlcoholDrinkData()
            .map { data -> data.map { it.mapLocalAlcoholDrinkToDomain() } }
    }

    override fun loadAllAlcoholDrinkDataById(id: Int): Flow<AlcoholDrink> {
        return localDataSource.loadAllAlcoholDrinkDataById(id)
            .map { it.mapLocalAlcoholDrinkToDomain() }
    }

    override fun insertAlcoholDrinkData(inputDrinkData: List<AlcoholDrink>) {
        localDataSource.insertAlcoholDrinkData(inputDrinkData.map { it.mapAlcoholDrinkToData() })
    }

    override fun updateAlcoholDrinkData(updateDrinkData: AlcoholDrink) {
        localDataSource.updateAlcoholDrinkData(updateDrinkData.mapAlcoholDrinkToData())
    }

    override fun deleteAllAlcoholDrinkData() {
        localDataSource.deleteAllAlcoholDrinkData()
    }
}
