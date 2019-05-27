package com.ian.app.drinkings.di

import androidx.room.Room
import com.ian.app.drinkings.data.localdata.MyDrinkingDatabase
import org.koin.dsl.module.module

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
object DatabaseModule {
    val databaseModule = module {
        // Room Database instance
        single { Room.databaseBuilder(get(), MyDrinkingDatabase::class.java, "DrinkingLocalData").build() }
        // localDao instance (get instance from MyDrinkingDatabase)
        single { get<MyDrinkingDatabase>().drinkDao() }
    }
}