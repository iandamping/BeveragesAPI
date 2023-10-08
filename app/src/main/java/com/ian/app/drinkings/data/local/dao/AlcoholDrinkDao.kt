package com.ian.app.drinkings.data.local.dao

import androidx.room.*
import com.ian.app.drinkings.data.local.entity.EntityAlcoholDrink
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
@Dao
interface AlcoholDrinkDao {
    @Query("SELECT * FROM alchohol_drink ")
    fun loadAllLocalData(): Flow<List<EntityAlcoholDrink>>

    @Query("SELECT * FROM alchohol_drink WHERE localDrinkId = :id")
    fun loadAllLocalDataById(id: Int): Flow<EntityAlcoholDrink>

    @Insert
    fun insertLocalData(inputDrinkData: List<EntityAlcoholDrink>?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateLocalData(updateDrinkData: EntityAlcoholDrink)

    @Query("DELETE FROM alchohol_drink")
    fun deleteAllLocalData()
}
