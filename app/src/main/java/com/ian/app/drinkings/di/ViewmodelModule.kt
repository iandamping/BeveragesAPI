package com.ian.app.drinkings.di

import com.ian.app.drinkings.data.viewmodel.GetAllDrinksCoroutineViewModel
import org.koin.dsl.module.module

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
object ViewmodelModule {
    val allViewmodelModule = module {
        factory { GetAllDrinksCoroutineViewModel(get(), get()) }
//        factory { GetAllDrinksViewModel(get(),get()) }
    }
}