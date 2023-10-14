package com.ian.app.drinkings.core.data.remote.api

import com.ian.app.drinkings.core.data.remote.model.GeneralDrinkData
import retrofit2.Response
import retrofit2.http.GET

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */

interface ApiInterface {
    companion object {
        private const val getNonAlcoholic = "filter.php?a=Non_Alcoholic"
        private const val getAlcoholic = "filter.php?a=Alcoholic"
    }

    @GET(getAlcoholic)
    suspend fun getAlcoholicDrinks(): Response<GeneralDrinkData<List<ResponseAlcoholDrink>>>

    @GET(getNonAlcoholic)
    suspend fun getNonAlcoholicDrinks(): Response<GeneralDrinkData<List<ResponseNonAlcoholDrink>>>
}
