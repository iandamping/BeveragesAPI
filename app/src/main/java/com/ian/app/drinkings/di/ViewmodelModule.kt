package com.ian.app.drinkings.di

import com.ian.app.drinkings.data.viewmodel.GetAllDrinksViewModel
import com.ian.app.drinkings.data.viewmodel.GetDetailDrinkViewModel
import com.ian.app.drinkings.data.viewmodel.GetDiscoverDrinkViewModel
import com.ian.app.drinkings.data.viewmodel.GetFilterDrinksViewModel
import org.koin.dsl.module.module

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
object ViewmodelModule {
    val allViewmodelModule = module {
        factory { GetAllDrinksViewModel(get()) }
        factory { GetDetailDrinkViewModel(get()) }
        factory { GetDiscoverDrinkViewModel(get()) }
        factory { GetFilterDrinksViewModel(get()) }
    }
}