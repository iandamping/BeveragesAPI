package com.ian.app.drinkings.di.domain

import com.ian.app.drinkings.core.data.repository.AlcoholBeverageRepositoryImpl
import com.ian.app.drinkings.core.domain.repository.AlcoholBeverageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AlcoholBeverageRepositoryModule {

    @Binds
    fun bindsAlcoholBeverageRepository(impl: AlcoholBeverageRepositoryImpl): AlcoholBeverageRepository
}
