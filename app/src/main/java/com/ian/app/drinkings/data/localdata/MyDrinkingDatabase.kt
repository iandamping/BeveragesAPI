package com.ian.app.drinkings.data.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
@Database(entities = [CocktailDrink.Drink::class], version = 1, exportSchema = false)
abstract class MyDrinkingDatabase : RoomDatabase() {

    abstract fun drinkDao(): CocktailDao
}