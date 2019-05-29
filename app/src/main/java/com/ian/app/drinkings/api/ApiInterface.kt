package com.ian.app.drinkings.api

import com.ian.app.drinkings.data.localdata.local_model.AlchoholDrink
import com.ian.app.drinkings.data.localdata.local_model.GeneralDrinkData
import com.ian.app.drinkings.data.localdata.local_model.NonAlchoholDrink
import com.ian.app.drinkings.di.NetworkingModule
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */

interface ApiInterface {

    @GET(NetworkingModule.getAlcoholic)
    fun getAlchoholicDrinks(): Observable<GeneralDrinkData<AlchoholDrink>>

    @GET(NetworkingModule.getNonAlcoholic)
    fun getNonAlchoholicDrinks(): Observable<GeneralDrinkData<NonAlchoholDrink>>

    @GET(NetworkingModule.getAlcoholic)
    fun getAlchoholicDrinksDef(): Deferred<GeneralDrinkData<AlchoholDrink>>

    @GET(NetworkingModule.getNonAlcoholic)
    fun getNonAlchoholicDrinksDef(): Deferred<GeneralDrinkData<NonAlchoholDrink>>
}