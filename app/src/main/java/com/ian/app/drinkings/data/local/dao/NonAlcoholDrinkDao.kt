package com.ian.app.drinkings.data.local.dao

import androidx.room.*
import com.ian.app.drinkings.data.local.entity.EntityNonAlcoholDrink
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 29/05/2019.
Github = https://github.com/iandamping
 */
@Dao
interface NonAlcoholDrinkDao {
    @Query("SELECT * FROM non_alchohol_drink ")
    fun loadAllLocalData(): Flow<List<EntityNonAlcoholDrink>>

    @Query("SELECT * FROM non_alchohol_drink WHERE localDrinkId = :id")
    fun loadAllLocalDataById(id: Int): Flow<EntityNonAlcoholDrink>

    @Insert
    fun insertLocalData(inputDrinkData: List<EntityNonAlcoholDrink>?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateLocalData(updateDrinkData: EntityNonAlcoholDrink)

    @Query("DELETE FROM non_alchohol_drink")
    fun deleteAllLocalData()
}
