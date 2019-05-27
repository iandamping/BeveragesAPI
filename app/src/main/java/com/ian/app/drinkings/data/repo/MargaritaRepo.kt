package com.ian.app.drinkings.data.repo

import com.ian.app.drinkings.api.ApiInterface
import com.ian.app.drinkings.data.localdata.CocktailDrink
import io.reactivex.Observable

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
class MargaritaRepo(private val api: ApiInterface) {

    fun getMargaritas(): Observable<List<CocktailDrink.Drink>> {
        return api.getCocktailDrink().flatMapIterable {
            it.cocktailDrinks
        }.map {
            return@map it
        }.toList().toObservable()
    }
}