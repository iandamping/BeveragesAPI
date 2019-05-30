package com.ian.app.drinkings.api

import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.model.GeneralDrinkData
import com.ian.app.drinkings.di.NetworkingModule
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */

interface ApiInterface {
//
//    @GET(NetworkingModule.getAlcoholic)
//    fun getAlchoholicDrinks(): Observable<GeneralDrinkData<AlchoholDrink>>
//
//    @GET(NetworkingModule.getNonAlcoholic)
//    fun getNonAlchoholicDrinks(): Observable<GeneralDrinkData<NonAlchoholDrink>>

    @GET(NetworkingModule.getAlcoholic)
    fun getAlchoholicDrinks(): Deferred<GeneralDrinkData<Drinks>>

    @GET(NetworkingModule.getNonAlcoholic)
    fun getNonAlchoholicDrinks(): Deferred<GeneralDrinkData<Drinks>>

    @GET(NetworkingModule.getRandomDrink)
    fun getRandomDrink(): Deferred<GeneralDrinkData<Drinks>>
}