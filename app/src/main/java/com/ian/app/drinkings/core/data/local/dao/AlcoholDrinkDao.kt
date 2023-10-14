package com.ian.app.drinkings.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ian.app.drinkings.core.data.local.entity.EntityAlcoholDrink
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
@Dao
interface AlcoholDrinkDao {
    @Query("SELECT * FROM alchohol_drink ")
    fun loadAllAlcoholDrinkData(): Flow<List<EntityAlcoholDrink>>

    @Query("SELECT * FROM alchohol_drink WHERE localDrinkId = :id")
    fun loadAllAlcoholDrinkDataById(id: Int): Flow<EntityAlcoholDrink>

    @Insert
    fun insertAlcoholDrinkData(inputDrinkData: List<EntityAlcoholDrink>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAlcoholDrinkData(updateDrinkData: EntityAlcoholDrink)

    @Query("DELETE FROM alchohol_drink")
    fun deleteAllAlcoholDrinkData()
}
