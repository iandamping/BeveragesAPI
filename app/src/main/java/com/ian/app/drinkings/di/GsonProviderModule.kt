package com.ian.app.drinkings.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GsonProviderModule {

    @Provides
    fun provideMoshi(): Gson {
        return Gson()
    }
}