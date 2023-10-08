package com.ian.app.drinkings.di

import android.content.Context
import androidx.room.Room
import com.ian.app.drinkings.data.local.BeveragesDatabase
import com.ian.app.drinkings.data.local.dao.AlcoholDrinkDao
import com.ian.app.drinkings.data.local.dao.NonAlcoholDrinkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "beverages.db"

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): BeveragesDatabase {
        return Room
            .databaseBuilder(context, BeveragesDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideBorneoPlacesDao(db: BeveragesDatabase): AlcoholDrinkDao {
        return db.alchoholDrinkDao()
    }

    @Provides
    fun provideFavoriteBorneoPlacesDao(db: BeveragesDatabase): NonAlcoholDrinkDao {
        return db.nonAlchoholDrinkDao()
    }
}
