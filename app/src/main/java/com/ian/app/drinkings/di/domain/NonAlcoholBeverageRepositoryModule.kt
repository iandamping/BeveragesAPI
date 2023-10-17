package com.ian.app.drinkings.di.domain

import com.ian.app.drinkings.core.data.repository.NonAlcoholBeverageRepositoryImpl
import com.ian.app.drinkings.core.domain.repository.NonAlcoholBeverageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NonAlcoholBeverageRepositoryModule {

    @Binds
    fun bindsNonAlcoholBeverageRepository(impl: NonAlcoholBeverageRepositoryImpl): NonAlcoholBeverageRepository
}
