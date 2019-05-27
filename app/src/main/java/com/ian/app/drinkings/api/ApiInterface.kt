package com.ian.app.drinkings.api

import com.ian.app.drinkings.data.localdata.CocktailDrink
import com.ian.app.drinkings.di.NetworkingModule.getMargarita
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */

interface ApiInterface {
    @GET(getMargarita)
    fun getCocktailDrink(): Observable<CocktailDrink>

}