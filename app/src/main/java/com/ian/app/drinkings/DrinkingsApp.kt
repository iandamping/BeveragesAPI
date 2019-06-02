package com.ian.app.drinkings

import android.app.Application
import com.google.gson.Gson
import com.ian.app.drinkings.di.NetworkingModule.networkModule
import com.ian.app.drinkings.di.ViewmodelModule.allViewmodelModule
import org.koin.android.ext.android.startKoin

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
class DrinkingsApp : Application() {
    companion object {
        lateinit var gson: Gson
    }

    override fun onCreate() {
        super.onCreate()
        gson = Gson()
        startKoin(this, listOf(networkModule, allViewmodelModule))
    }
}