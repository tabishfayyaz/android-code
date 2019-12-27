package com.example.tmdbclientrx.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun getService () : MovieDataService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //for rxjava based callback
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(MovieDataService::class.java)
        }
    }
}