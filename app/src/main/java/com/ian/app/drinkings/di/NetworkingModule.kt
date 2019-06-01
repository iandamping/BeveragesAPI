package com.ian.app.drinkings.di

import com.google.gson.GsonBuilder
import com.ian.app.drinkings.api.ApiInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
object NetworkingModule {
    private const val baseUrl = "https://www.thecocktaildb.com/api/json/v1/1/"
    const val getNonAlcoholic = "filter.php?a=Non_Alcoholic"
    const val getAlcoholic = "filter.php?a=Alcoholic"
    const val getOptionalAlchoholic = "filter.php?a=Optional alcohol"
    const val getRandomDrink = "random.php"
    const val getDetailedDrink = "lookup.php"
    const val getListsData = "list"
    const val getListsPhp = "list.php"

    val networkModule = module {
        single { createOkHttpClient() }
        single { createClient<ApiInterface>(get()) }
    }

    private fun createOkHttpClient(): OkHttpClient {
        val timeOut = 60L
        val dispatcher = Dispatcher().apply {
            maxRequests = 20
            maxRequestsPerHost = 20
        }

        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .dispatcher(dispatcher)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .addInterceptor { chain ->
                    val ongoing = chain.request().newBuilder()
//                ongoing.addHeader(ctx.resources.getString(R.string.retrofit_header1), ctx.resources.getString(R.string.fcm_key))
//                ongoing.addHeader(ctx.resources.getString(R.string.retrofit_header2), ctx.resources.getString(R.string.retrofit_value_header2))
//                ongoing.addHeader(BeverageConstant.NEW_X_OC_MERCHANT_ID, BeverageConstant.NEW_X_OC_MERCHANT_VALUE);
                    chain.proceed(ongoing.build())
                }
        return okHttpBuilder.build()
    }

    private inline fun <reified T> createClient(okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .baseUrl(baseUrl)
                .build()
        return retrofit.create(T::class.java)
    }
}