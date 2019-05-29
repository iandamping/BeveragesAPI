package com.ian.app.drinkings.data.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ian.app.drinkings.data.localdata.dao.AlchoholDrinkDao
import com.ian.app.drinkings.data.localdata.dao.NonAlcoholDrinkDao
import com.ian.app.drinkings.data.localdata.local_model.AlchoholDrink
import com.ian.app.drinkings.data.localdata.local_model.NonAlchoholDrink

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
@Database(entities = [AlchoholDrink::class, NonAlchoholDrink::class], version = 1, exportSchema = false)
abstract class MyDrinkingDatabase : RoomDatabase() {

    abstract fun alchoholDrinkDao(): AlchoholDrinkDao
    abstract fun nonAlchoholDrinkDao(): NonAlcoholDrinkDao
}