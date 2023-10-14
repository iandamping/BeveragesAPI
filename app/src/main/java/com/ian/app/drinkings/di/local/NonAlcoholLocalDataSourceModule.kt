package com.ian.app.drinkings.di.local

import com.ian.app.drinkings.core.data.local.NonAlcoholLocalDataSource
import com.ian.app.drinkings.core.data.local.NonAlcoholLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NonAlcoholLocalDataSourceModule {

    @Binds
    fun bindsNonAlcoholLocalDataSource(impl: NonAlcoholLocalDataSourceImpl): NonAlcoholLocalDataSource
}
