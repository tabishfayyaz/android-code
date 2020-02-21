package com.example.countriesapp.di

import com.example.countriesapp.model.CountriesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://raw.githubusercontent.com/"

    @Provides
    fun provideCountriesApi () : CountriesApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //for rxjava based callback, converts the API response into observable/single or what have you
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(CountriesApi::class.java)
    }
}