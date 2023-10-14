package com.ian.app.drinkings.core.data.local

import com.ian.app.drinkings.core.data.local.dao.AlcoholDrinkDao
import com.ian.app.drinkings.core.data.local.entity.EntityAlcoholDrink
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlcoholLocalDataSourceImpl @Inject constructor(
    private val alcoholDrinkDao: AlcoholDrinkDao,
) : AlcoholLocalDataSource {
    override fun loadAllAlcoholDrinkData(): Flow<List<EntityAlcoholDrink>> {
        return alcoholDrinkDao.loadAllAlcoholDrinkData()
    }

    override fun loadAllAlcoholDrinkDataById(id: Int): Flow<EntityAlcoholDrink> {
        return alcoholDrinkDao.loadAllAlcoholDrinkDataById(id)
    }

    override fun insertAlcoholDrinkData(inputDrinkData: List<EntityAlcoholDrink>) {
        alcoholDrinkDao.insertAlcoholDrinkData(inputDrinkData)
    }

    override fun updateAlcoholDrinkData(updateDrinkData: EntityAlcoholDrink) {
        alcoholDrinkDao.updateAlcoholDrinkData(updateDrinkData)
    }

    override fun deleteAllAlcoholDrinkData() {
        alcoholDrinkDao.deleteAllAlcoholDrinkData()
    }
}
