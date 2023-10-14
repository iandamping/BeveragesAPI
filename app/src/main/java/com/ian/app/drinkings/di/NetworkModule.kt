package com.ian.app.drinkings.di

import com.google.gson.GsonBuilder
import com.ian.app.drinkings.core.data.remote.api.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val baseUrl = "https://www.thecocktaildb.com/api/json/v1/1/"

    @Provides
    fun provideHttpClientForHomeScreen(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val builder = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideApiInterfaceForHomeScreen(
        okHttpClient: OkHttpClient
    ): ApiInterface {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl(baseUrl)
            .build()
            .create(ApiInterface::class.java)
    }
}
