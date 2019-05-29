package com.ian.app.drinkings.data.localdata.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ian.app.drinkings.data.localdata.local_model.AlchoholDrink

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
@Dao
interface AlchoholDrinkDao {
    @Query("SELECT * FROM alchohol_drink ")
    fun loadAllLocalData(): LiveData<List<AlchoholDrink>>

    @Query("SELECT * FROM alchohol_drink WHERE local_drink_id = :id")
    fun loadAllLocalDataById(id: Int?): LiveData<AlchoholDrink>

    @Insert
    fun insertLocalData(inputDrinkData: List<AlchoholDrink>?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateLocalData(updateDrinkData: AlchoholDrink)

    @Query("DELETE FROM alchohol_drink")
    fun deleteAllLocalData()
}