package com.ian.app.drinkings.core.data.local

import com.ian.app.drinkings.core.data.local.dao.NonAlcoholDrinkDao
import com.ian.app.drinkings.core.data.local.entity.EntityDetailNonAlcoholDrink
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NonAlcoholLocalDataSourceImpl @Inject constructor(
    private val nonAlcoholDrinkDao: NonAlcoholDrinkDao
) : NonAlcoholLocalDataSource {
    override fun loadAllNonAlcoholDrinkData(): Flow<List<EntityDetailNonAlcoholDrink>> {
        return nonAlcoholDrinkDao.loadAllNonAlcoholDrinkData()
    }

    override fun loadAllNonAlcoholDrinkDataById(id: Int): Flow<EntityDetailNonAlcoholDrink> {
        return nonAlcoholDrinkDao.loadAllNonAlcoholDrinkDataById(id)
    }

    override fun insertNonAlcoholDrinkData(inputDrinkData: List<EntityDetailNonAlcoholDrink>) {
        nonAlcoholDrinkDao.insertNonAlcoholDrinkData(inputDrinkData)
    }

    override fun updateNonAlcoholDrinkData(updateDrinkData: EntityDetailNonAlcoholDrink) {
        nonAlcoholDrinkDao.updateNonAlcoholDrinkData(updateDrinkData)
    }

    override fun deleteAllNonAlcoholDrinkData() {
        nonAlcoholDrinkDao.deleteAllNonAlcoholDrinkData()
    }
}
