package com.ian.app.drinkings.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ian.app.drinkings.core.data.local.entity.EntityNonAlcoholDrink
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 29/05/2019.
Github = https://github.com/iandamping
 */
@Dao
interface NonAlcoholDrinkDao {
    @Query("SELECT * FROM non_alchohol_drink ")
    fun loadAllNonAlcoholDrinkData(): Flow<List<EntityNonAlcoholDrink>>

    @Query("SELECT * FROM non_alchohol_drink WHERE localDrinkId = :id")
    fun loadAllNonAlcoholDrinkDataById(id: Int): Flow<EntityNonAlcoholDrink>

    @Insert
    fun insertNonAlcoholDrinkData(inputDrinkData: List<EntityNonAlcoholDrink>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNonAlcoholDrinkData(updateDrinkData: EntityNonAlcoholDrink)

    @Query("DELETE FROM non_alchohol_drink")
    fun deleteAllNonAlcoholDrinkData()
}
