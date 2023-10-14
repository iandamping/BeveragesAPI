package com.ian.app.drinkings.core.data.local

import com.ian.app.drinkings.core.data.local.dao.NonAlcoholDrinkDao
import com.ian.app.drinkings.core.data.local.entity.EntityNonAlcoholDrink
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NonAlcoholLocalDataSourceImpl @Inject constructor(
    private val nonAlcoholDrinkDao: NonAlcoholDrinkDao
) : NonAlcoholLocalDataSource {
    override fun loadAllNonAlcoholDrinkData(): Flow<List<EntityNonAlcoholDrink>> {
        return nonAlcoholDrinkDao.loadAllNonAlcoholDrinkData()
    }

    override fun loadAllNonAlcoholDrinkDataById(id: Int): Flow<EntityNonAlcoholDrink> {
        return nonAlcoholDrinkDao.loadAllNonAlcoholDrinkDataById(id)
    }

    override fun insertNonAlcoholDrinkData(inputDrinkData: List<EntityNonAlcoholDrink>) {
        nonAlcoholDrinkDao.insertNonAlcoholDrinkData(inputDrinkData)
    }

    override fun updateNonAlcoholDrinkData(updateDrinkData: EntityNonAlcoholDrink) {
        nonAlcoholDrinkDao.updateNonAlcoholDrinkData(updateDrinkData)
    }

    override fun deleteAllNonAlcoholDrinkData() {
        nonAlcoholDrinkDao.deleteAllNonAlcoholDrinkData()
    }
}
