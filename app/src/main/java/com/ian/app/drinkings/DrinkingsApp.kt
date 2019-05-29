package com.ian.app.drinkings

import android.app.Application
import com.ian.app.drinkings.di.DatabaseModule.databaseModule
import com.ian.app.drinkings.di.NetworkingModule.networkModule
import com.ian.app.drinkings.di.RepositoryModule.allRepoModule
import com.ian.app.drinkings.di.ViewmodelModule.allViewmodelModule
import org.koin.android.ext.android.startKoin

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
class DrinkingsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkModule, databaseModule, allViewmodelModule, allRepoModule))
    }
}