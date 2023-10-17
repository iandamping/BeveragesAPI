package com.ian.app.drinkings.core.data.repository

import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.core.data.local.AlcoholLocalDataSource
import com.ian.app.drinkings.core.data.remote.BeverageRemoteDataSource
import com.ian.app.drinkings.core.domain.model.AlcoholDrink
import com.ian.app.drinkings.core.domain.model.DetailAlcoholDrink
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
            is DataSource.Success ->
                if (data.data.cocktailDrinks != null) {
                    DomainSource.Success(
                        data.data.cocktailDrinks.map { it.mapRemoteAlcoholDrinkToDomain() }
                    )
                } else {
                    DomainSource.Error("null data")
                }
        }
    }

    override suspend fun getAlcoholicDrinksById(id: Int): DomainSource<DetailAlcoholDrink> {
        return when (val data = remoteDataSource.getAlcoholicDrinksById(id)) {
            is DataSource.Error -> DomainSource.Error(data.errorMessage)
            is DataSource.Success -> DomainSource.Success(
                data.data.mapRemoteAlcoholDrinkToDomain()
            )
        }
    }

    override fun loadDetailAllAlcoholDrinkData(): Flow<List<DetailAlcoholDrink>> {
        return localDataSource.loadAllAlcoholDrinkData()
            .map { data -> data.map { it.mapLocalAlcoholDrinkToDomain() } }
    }

    override fun loadDetailAllAlcoholDrinkDataById(id: Int): Flow<DetailAlcoholDrink> {
        return localDataSource.loadAllAlcoholDrinkDataById(id)
            .map { it.mapLocalAlcoholDrinkToDomain() }
    }

    override fun insertDetailAlcoholDrinkData(inputDrinkData: List<DetailAlcoholDrink>) {
        localDataSource.insertAlcoholDrinkData(inputDrinkData.map { it.mapAlcoholDrinkToData() })
    }

    override fun updateDetailAlcoholDrinkData(updateDrinkData: DetailAlcoholDrink) {
        localDataSource.updateAlcoholDrinkData(updateDrinkData.mapAlcoholDrinkToData())
    }

    override fun deleteAllDetailAlcoholDrinkData() {
        localDataSource.deleteAllAlcoholDrinkData()
    }
}
