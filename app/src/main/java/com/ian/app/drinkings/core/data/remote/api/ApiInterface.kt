package com.ian.app.drinkings.core.data.remote.api

import com.ian.app.drinkings.core.data.remote.model.GeneralDrinkData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */

interface ApiInterface {
    companion object {
        private const val getNonAlcoholic = "filter.php?a=Non_Alcoholic"
        private const val getAlcoholic = "filter.php?a=Alcoholic"
        private const val getDetailDrink = "lookup.php"
    }

    @GET(getAlcoholic)
    suspend fun getAlcoholicDrinks(): Response<GeneralDrinkData<List<ResponseAlcoholDrink>>>

    @GET(getNonAlcoholic)
    suspend fun getNonAlcoholicDrinks(): Response<GeneralDrinkData<List<ResponseNonAlcoholDrink>>>

    @GET("$getDetailDrink/{i}")
    suspend fun getAlcoholicDrinksById(@Path("i") id: Int): Response<GeneralDrinkData<List<ResponseDetailAlcoholDrink>>>

    @GET("$getDetailDrink/{i}")
    suspend fun getNonAlcoholicDrinksById(
        @Path(
            "i"
        ) id: Int
    ): Response<GeneralDrinkData<List<ResponseDetailNonAlcoholDrink>>>
}
