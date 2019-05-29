package com.ian.app.drinkings.data.repo

import com.ian.app.drinkings.api.ApiInterface
import com.ian.app.drinkings.data.localdata.local_model.AlchoholDrink
import com.ian.app.drinkings.data.localdata.local_model.NonAlchoholDrink
import io.reactivex.Observable

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
class AllDrinksRepo(private val api: ApiInterface) {

    fun getAlchoholDrink(): Observable<List<AlchoholDrink>> {
        return api.getAlchoholicDrinks().flatMapIterable {
            it.cocktailDrinks
        }.map {
            return@map it
        }.toList().toObservable()
    }

    fun getNonAlchoholDrink(): Observable<List<NonAlchoholDrink>> {
        return api.getNonAlchoholicDrinks().flatMapIterable {
            it.cocktailDrinks
        }.map {
            return@map it
        }.toList().toObservable()
    }
}