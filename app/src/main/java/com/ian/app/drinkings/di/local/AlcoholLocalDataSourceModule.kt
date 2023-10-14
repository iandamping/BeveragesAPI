package com.ian.app.drinkings.di.local

import com.ian.app.drinkings.core.data.local.AlcoholLocalDataSource
import com.ian.app.drinkings.core.data.local.AlcoholLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AlcoholLocalDataSourceModule {

    @Binds
    fun bindsAlcoholLocalDataSource(impl: AlcoholLocalDataSourceImpl): AlcoholLocalDataSource
}
