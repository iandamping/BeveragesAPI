package com.ian.app.drinkings.data.localdata

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
@Dao
interface CocktailDao {
    @Query("SELECT * FROM local_drink ")
    fun loadAllLocalData(): LiveData<List<CocktailDrink.Drink>>

    @Query("SELECT * FROM local_drink WHERE local_drink_id = :id")
    fun loadAllLocalDataById(id: Int?): LiveData<CocktailDrink.Drink>

    @Insert
    fun insertLocalData(inputDrink: List<CocktailDrink.Drink>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateLocalData(updateDrink: CocktailDrink.Drink)

    @Query("DELETE FROM local_drink")
    fun deleteAllLocalData()
}