package com.ian.app.drinkings.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ian.app.drinkings.core.data.local.dao.AlcoholDrinkDao
import com.ian.app.drinkings.core.data.local.dao.NonAlcoholDrinkDao
import com.ian.app.drinkings.core.data.local.entity.EntityDetailAlcoholDrink
import com.ian.app.drinkings.core.data.local.entity.EntityDetailNonAlcoholDrink

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
@Database(
    entities = [EntityDetailAlcoholDrink::class, EntityDetailNonAlcoholDrink::class],
    version = 1,
    exportSchema = false
)
abstract class BeveragesDatabase : RoomDatabase() {

    abstract fun alchoholDrinkDao(): AlcoholDrinkDao

    abstract fun nonAlchoholDrinkDao(): NonAlcoholDrinkDao
}
