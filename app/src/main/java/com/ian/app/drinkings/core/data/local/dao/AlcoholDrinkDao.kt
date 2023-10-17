package com.ian.app.drinkings.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ian.app.drinkings.core.data.local.entity.EntityDetailAlcoholDrink
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
@Dao
interface AlcoholDrinkDao {
    @Query("SELECT * FROM alchohol_drink ")
    fun loadAllAlcoholDrinkData(): Flow<List<EntityDetailAlcoholDrink>>

    @Query("SELECT * FROM alchohol_drink WHERE localDrinkId = :id")
    fun loadAllAlcoholDrinkDataById(id: Int): Flow<EntityDetailAlcoholDrink>

    @Insert
    fun insertAlcoholDrinkData(inputDrinkData: List<EntityDetailAlcoholDrink>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAlcoholDrinkData(updateDrinkData: EntityDetailAlcoholDrink)

    @Query("DELETE FROM alchohol_drink")
    fun deleteAllAlcoholDrinkData()
}
