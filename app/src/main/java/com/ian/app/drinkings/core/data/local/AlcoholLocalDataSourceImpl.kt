package com.ian.app.drinkings.core.data.local

import com.ian.app.drinkings.core.data.local.dao.AlcoholDrinkDao
import com.ian.app.drinkings.core.data.local.entity.EntityDetailAlcoholDrink
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlcoholLocalDataSourceImpl @Inject constructor(
    private val alcoholDrinkDao: AlcoholDrinkDao,
) : AlcoholLocalDataSource {
    override fun loadAllAlcoholDrinkData(): Flow<List<EntityDetailAlcoholDrink>> {
        return alcoholDrinkDao.loadAllAlcoholDrinkData()
    }

    override fun loadAllAlcoholDrinkDataById(id: Int): Flow<EntityDetailAlcoholDrink> {
        return alcoholDrinkDao.loadAllAlcoholDrinkDataById(id)
    }

    override fun insertAlcoholDrinkData(inputDrinkData: List<EntityDetailAlcoholDrink>) {
        alcoholDrinkDao.insertAlcoholDrinkData(inputDrinkData)
    }

    override fun updateAlcoholDrinkData(updateDrinkData: EntityDetailAlcoholDrink) {
        alcoholDrinkDao.updateAlcoholDrinkData(updateDrinkData)
    }

    override fun deleteAllAlcoholDrinkData() {
        alcoholDrinkDao.deleteAllAlcoholDrinkData()
    }
}
