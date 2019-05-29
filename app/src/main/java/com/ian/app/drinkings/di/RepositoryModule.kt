package com.ian.app.drinkings.di

import com.ian.app.drinkings.data.repo.AllDrinksRepo
import org.koin.dsl.module.module

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
object RepositoryModule {
    val allRepoModule = module {
        single { AllDrinksRepo(get()) }
    }
}