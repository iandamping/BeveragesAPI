package com.ian.app.drinkings.api

import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.model.GeneralDrinkData
import com.ian.app.drinkings.di.NetworkingModule
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */

interface ApiInterface {

    @GET(NetworkingModule.getAlcoholic)
    fun getAlchoholicDrinks(): Deferred<GeneralDrinkData<Drinks>>

    @GET(NetworkingModule.getNonAlcoholic)
    fun getNonAlchoholicDrinks(): Deferred<GeneralDrinkData<Drinks>>

    @GET(NetworkingModule.getOptionalAlchoholic)
    fun getOptionalAlchoholicDrinks(): Deferred<GeneralDrinkData<Drinks>>

    @GET(NetworkingModule.getRandomDrink)
    fun getRandomDrink(): Deferred<GeneralDrinkData<Drinks>>

    @GET(NetworkingModule.getDetailedDrink)
    fun getDetailedDrink(@Query("i") foodID: String): Deferred<GeneralDrinkData<Drinks>>

    @GET(NetworkingModule.getListsPhp)
    fun getAllDrinkCategories(@Query ("c") category:String):Deferred<GeneralDrinkData<Drinks>>

    @GET(NetworkingModule.getListsPhp)
    fun getAllDrinkGlasses(@Query ("g") category:String):Deferred<GeneralDrinkData<Drinks>>

    @GET(NetworkingModule.getListsPhp)
    fun getAllDrinkIngredients(@Query ("i") category:String):Deferred<GeneralDrinkData<Drinks>>
}