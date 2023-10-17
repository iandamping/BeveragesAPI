package com.ian.app.drinkings.core.data.remote

import com.ian.app.drinkings.core.DataSource
import com.ian.app.drinkings.core.data.remote.api.ApiInterface
import com.ian.app.drinkings.core.data.remote.api.ResponseAlcoholDrink
import com.ian.app.drinkings.core.data.remote.api.ResponseDetailAlcoholDrink
import com.ian.app.drinkings.core.data.remote.api.ResponseDetailNonAlcoholDrink
import com.ian.app.drinkings.core.data.remote.api.ResponseNonAlcoholDrink
import com.ian.app.drinkings.core.data.remote.model.GeneralDrinkData
import javax.inject.Inject

class BeverageRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface) :
    BeverageRemoteDataSource {
    override suspend fun getAlcoholicDrinks(): DataSource<GeneralDrinkData<List<ResponseAlcoholDrink>>> {
        return try {
            val data = apiInterface.getAlcoholicDrinks()
            if (data.isSuccessful) {
                if (data.body() != null) {
                    DataSource.Success(data = checkNotNull(data.body()))
                } else {
                    DataSource.Error("Error from retrofit : Body null")
                }
            } else {
                DataSource.Error(
                    "Error from retrofit Http Code is not 200 .. 300 with message : ${
                        data.errorBody()?.string()
                    }"
                )
            }
        } catch (e: IllegalArgumentException) {
            DataSource.Error(e.message ?: "default error")
        }
    }

    override suspend fun getAlcoholicDrinksById(id: Int): DataSource<ResponseDetailAlcoholDrink> {
        return try {
            val data = apiInterface.getAlcoholicDrinksById(id)
            if (data.isSuccessful) {
                if (data.body() != null) {
                    val value = checkNotNull(data.body()).cocktailDrinks
                    if (value != null) {
                        DataSource.Success(data = value.first())
                    } else {
                        DataSource.Error("null")
                    }
                } else {
                    DataSource.Error("Error from retrofit : Body null")
                }
            } else {
                DataSource.Error(
                    "Error from retrofit Http Code is not 200 .. 300 with message : ${
                        data.errorBody()?.string()
                    }"
                )
            }
        } catch (e: IllegalArgumentException) {
            DataSource.Error(e.message ?: "default error")
        }
    }

    override suspend fun getNonAlcoholicDrinks(): DataSource<GeneralDrinkData<List<ResponseNonAlcoholDrink>>> {
        return try {
            val data = apiInterface.getNonAlcoholicDrinks()
            if (data.isSuccessful) {
                if (data.body() != null) {
                    DataSource.Success(data = checkNotNull(data.body()))
                } else {
                    DataSource.Error("Error from retrofit : Body null")
                }
            } else {
                DataSource.Error(
                    "Error from retrofit Http Code is not 200 .. 300 with message : ${
                        data.errorBody()?.string()
                    }"
                )
            }
        } catch (e: IllegalArgumentException) {
            DataSource.Error(e.message ?: "default error")
        }
    }

    override suspend fun getNonAlcoholicDrinksById(id: Int): DataSource<ResponseDetailNonAlcoholDrink> {
        return try {
            val data = apiInterface.getNonAlcoholicDrinksById(id)
            if (data.isSuccessful) {
                if (data.body() != null) {
                    val value = checkNotNull(data.body()).cocktailDrinks
                    if (value != null) {
                        DataSource.Success(data = value.first())
                    } else {
                        DataSource.Error("null")
                    }
                } else {
                    DataSource.Error("Error from retrofit : Body null")
                }
            } else {
                DataSource.Error(
                    "Error from retrofit Http Code is not 200 .. 300 with message : ${
                        data.errorBody()?.string()
                    }"
                )
            }
        } catch (e: IllegalArgumentException) {
            DataSource.Error(e.message ?: "default error")
        }
    }
}
