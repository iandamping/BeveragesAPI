package com.ian.app.drinkings.data.localdata.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ian.app.drinkings.data.localdata.local_model.NonAlchoholDrink

/**
 *
Created by Ian Damping on 29/05/2019.
Github = https://github.com/iandamping
 */
@Dao
interface NonAlcoholDrinkDao {
    @Query("SELECT * FROM non_alchohol_drink ")
    fun loadAllLocalData(): LiveData<List<NonAlchoholDrink>>

    @Query("SELECT * FROM non_alchohol_drink WHERE local_drink_id = :id")
    fun loadAllLocalDataById(id: Int?): LiveData<NonAlchoholDrink>

    @Insert
    fun insertLocalData(inputDrinkData: List<NonAlchoholDrink>?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateLocalData(updateDrinkData: NonAlchoholDrink)

    @Query("DELETE FROM non_alchohol_drink")
    fun deleteAllLocalData()
}