package com.ian.app.drinkings.di

import com.ian.app.drinkings.data.repo.MargaritaRepo
import org.koin.dsl.module.module

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
object RepositoryModule {
    val allRepoModule = module {
        single { MargaritaRepo(get()) }
    }
}